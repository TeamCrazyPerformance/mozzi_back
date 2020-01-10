package com.tcp.mozzi.back.controller.user;

import com.tcp.mozzi.back.domain.user.User;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import com.tcp.mozzi.back.dto.user.*;
import com.tcp.mozzi.back.service.user.UserService;
import com.tcp.mozzi.back.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/user")
@RestController
@Api(tags = "User", description = "사용자")
public class UserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @ApiOperation(value = "가입신청", notes = "회원 가입 신청을 합니다.")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequestDto userDto) {
        if(userService.isExistUserByName(userDto.getId())){
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.BAD_REQUEST);
        }
        userService.addUser(userDto.toEntity());

        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.CREATED);
    }

    @PutMapping("/check")
    @ResponseBody
    @ApiOperation(value = "아이디 중복확인", notes = "해당 아이디의 사용가능 여부를 확인합니다.")
    public CheckUserResponseDto checkUser(@Valid @RequestBody CheckUserRequestDto requestDto) {
        return new CheckUserResponseDto(!userService.isExistUserByName(requestDto.getName()));
    }

    @DeleteMapping("")
    @ResponseBody
    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴 신청을 합니다.")
    public ResponseEntity<?> deleteUser(HttpServletRequest httpRequest){
        final String token = httpRequest.getHeader(tokenHeader).substring(7);
        System.out.println(jwtTokenUtil.getIdFromToken(token));

        //userService.getUserById(Integer.parseInt(userId));

        return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.GONE);
    }

    @PutMapping("")
    @ResponseBody
    @ApiOperation(value = "정보 수정", notes = "유저 정보를 수정합니다.")
    public ResponseEntity<?> updateUser(HttpServletRequest httpRequest, @Valid @RequestBody UpdateUserRequestDto updateUserRequestDto){
        final String token = httpRequest.getHeader(tokenHeader).substring(7);
        User user = userService.getUserById(jwtTokenUtil.getIdFromToken(token));
        user.updateUser(updateUserRequestDto);
        userService.updateUser(user);

        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.OK);
    }

    @PutMapping("/password")
    @ResponseBody
    @ApiOperation(value = "비밀번호 변경", notes = "비밀번호를 변경합니다.")
    public ResponseEntity<?> updateUserPassword(HttpServletRequest httpRequest, @Valid @RequestBody UpdateUserPasswordRequestDto updateUserPasswordRequestDto){
        final String token = httpRequest.getHeader(tokenHeader).substring(7);
        User user = userService.getUserById(jwtTokenUtil.getIdFromToken(token));
        if(userService.isValidUser(user, updateUserPasswordRequestDto.getCurPassword())){
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.BAD_REQUEST);
        }
        userService.updateUserPassword(user, updateUserPasswordRequestDto.getNewPassword());

        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "정보 보기", notes = "해당 유저의 공개된 정보를 확인합니다.")
    public ResponseEntity<?> userDetail(HttpServletRequest httpRequest, @PathVariable("id") String id){
        final String token = httpRequest.getHeader(tokenHeader).substring(7);
        User user = userService.getUserById(jwtTokenUtil.getIdFromToken(token));

        return new ResponseEntity<>(new UserDetailResponseDto(userService.userDetailById(Integer.parseInt(id), user.getRole().toString())), HttpStatus.OK);
    }


}
