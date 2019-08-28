package com.tcp.mozzi.back.controller.storage;


import com.tcp.mozzi.back.dto.storage.UploadFileResponseDto;
import com.tcp.mozzi.back.service.storage.FileStorageService;
import com.tcp.mozzi.back.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public ResponseEntity<?> getList(@PathVariable("dirId") String dirId, HttpServletRequest request){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader));


        return null;
    }

    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "폴더 생성", notes = "폴더를 생성합니다.")
    public ResponseEntity<?> createFolder(String curDirId, HttpServletRequest request){

        return null;
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    @ApiOperation(value = "파일 업로드", notes = "파일을 현재 폴더에 업로드합니다.")
    public UploadFileResponseDto uploadFile(@RequestParam("storage") MultipartFile file, String curDirId){
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponseDto(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    @ResponseBody
    @ApiOperation(value = "파일 다운로드", notes = "파일을 다운로드합니다.")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = fileStorageService.loadFileAsResource(fileName);

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
