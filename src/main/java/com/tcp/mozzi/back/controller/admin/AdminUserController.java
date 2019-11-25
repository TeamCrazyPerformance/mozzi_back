package com.tcp.mozzi.back.controller.admin;

import com.tcp.mozzi.back.dto.DefaultResponseDto;
import com.tcp.mozzi.back.dto.admin.ReadWaitUserResponseDto;
import com.tcp.mozzi.back.service.admin.AdminService;
import com.tcp.mozzi.back.service.log.LogService;
import com.tcp.mozzi.back.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/admin")
@RestController
@Api(tags = "Admin", description = "관리자")
public class AdminUserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LogService logService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/user")
    @ResponseBody
    @ApiOperation(value = "대기 유저 목록 보기", notes = "가입신청을 한 유저의 목록을 불러옵니다.")
    public ResponseEntity<?> readWaitUser(HttpServletRequest request,
                                      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){
        final String token = request.getHeader(tokenHeader).substring(7);
        if(jwtTokenUtil.getRoleFromToken(token) == "USER"){
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.UNAUTHORIZED);
        }


        return new ResponseEntity<>(new ReadWaitUserResponseDto(adminService.getWaitUsers((page-1)*limit, limit), page, adminService.totalWaitUsers()), HttpStatus.OK);
    }

    @PutMapping("/approve/{userId}")
    @ResponseBody
    @ApiOperation(value = "가입 승인", notes = "가입 신청한 유저를 승인합니다.")
    public ResponseEntity<?> approveUser(HttpServletRequest request,
                                         @PathVariable(value = "userId") int userId,
                                         @RequestBody String body){
        final String token = request.getHeader(tokenHeader).substring(7);
        if(jwtTokenUtil.getRoleFromToken(token) == "USER"){
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.UNAUTHORIZED);
        }

        System.out.println(body);


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
