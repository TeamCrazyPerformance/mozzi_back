package com.tcp.mozzi.back.controller.user;

import com.tcp.mozzi.back.dto.ResponseMessage;
import com.tcp.mozzi.back.dto.user.UserDTO;
import com.tcp.mozzi.back.security.JwtTokenUtil;
import com.tcp.mozzi.back.service.user.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/user")
@RestController
@Api(value = "User", description = "유저", tags = "User")
public class UserController {

    @Autowired
    private JwtTokenUtil tokenUtil;


    @Autowired
    private UserService userService;

    UserController(UserService userService) { this.userService = userService; }

    @ApiOperation(value = "회원가입", notes = "회원정보를 입력하고 회원가입을 신청합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> signUp(@RequestBody UserDTO user){
        boolean userCreate = userService.createUser(user);

        if(userCreate)
            return new ResponseEntity<>(new ResponseMessage(userCreate), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "로그인", notes = "아이디, 패스워드로 로그인을 하여 토큰을 발급받습니다.")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void signIn(@RequestBody String id, @RequestBody String password){

    }

    private void authenticate(String id, String password){
        Objects.requireNonNull(id);
        Objects.requireNonNull(password);


    }

}
