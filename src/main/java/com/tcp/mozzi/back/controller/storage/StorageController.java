package com.tcp.mozzi.back.controller.storage;


import com.tcp.mozzi.back.domain.storage.Storage;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import com.tcp.mozzi.back.dto.storage.ReadStorageResponseDto;
import com.tcp.mozzi.back.dto.storage.UploadFileResponseDto;
import com.tcp.mozzi.back.service.storage.FileStorageService;
import com.tcp.mozzi.back.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

@RequestMapping("/storage")
@RestController
@Api(tags = "Storage", description = "저장소")
public class StorageController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/{dirId}")
    @ResponseBody
    @ApiOperation(value = "폴더 보기", notes = "해당 폴더에 있는 파일, 폴더를 확인합니다.")
    public ResponseEntity<?> getList(@PathVariable("dirId") String dirIdString, HttpServletRequest request){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader).substring(7));
        int dirId;
        if(dirIdString.length() == 0){
            dirId = 0;
        }else{
            dirId = Integer.parseInt(dirIdString);
        }

        
        return new ResponseEntity<>(new ReadStorageResponseDto(fileStorageService.readStorage(dirId,userId)), HttpStatus.OK);
    }

    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "폴더 생성", notes = "폴더를 생성합니다.")
    public ResponseEntity<?> createFolder(String dirName, String curDirId, HttpServletRequest request){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader).substring(7));
        fileStorageService.createStorage(dirName, Integer.parseInt(curDirId), userId);

        return this.getList(curDirId, request);
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    @ApiOperation(value = "파일 업로드", notes = "파일을 현재 폴더에 업로드합니다.")
    public ResponseEntity<?> uploadFile(@RequestParam("storage") MultipartFile file, String curDirId, HttpServletRequest request){
        final String token = request.getHeader(tokenHeader).substring(7);
        int userId = jwtTokenUtil.getIdFromToken(token);
        String userName = jwtTokenUtil.getUsernameFromToken(token);
        String fileName = fileStorageService.storeFile(file, userName, Integer.parseInt(curDirId), userId);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return this.getList(curDirId, request);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    @ResponseBody
    @ApiOperation(value = "파일 다운로드", notes = "파일을 다운로드합니다.")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName, String curDirId, HttpServletRequest request){
        final String token = request.getHeader(tokenHeader).substring(7);
        int userId = jwtTokenUtil.getIdFromToken(token);
        Resource resource = fileStorageService.loadFileAsResource(fileName, Integer.parseInt(curDirId), userId);

        if(resource == null)
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.NOT_ACCEPTABLE);

        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch(IOException e){
            System.out.println("Could not determine storage type " + fileName + " : " + e);
        }

        if(contentType == null)
            contentType = "application/octet-stream";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
