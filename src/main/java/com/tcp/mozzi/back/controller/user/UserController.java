package com.tcp.mozzi.back.controller.user;

import com.tcp.mozzi.back.dto.DefaultResponseDto;
import com.tcp.mozzi.back.dto.user.CheckUserRequestDto;
import com.tcp.mozzi.back.dto.user.CheckUserResponseDto;
import com.tcp.mozzi.back.dto.user.RegisterUserRequestDto;
import com.tcp.mozzi.back.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DefaultResponseDto registerUser(@Valid @RequestBody RegisterUserRequestDto userDto) {
        userService.addUser(userDto.toEntity());

        return new DefaultResponseDto();
    }

    @PutMapping("/check")
    @ResponseBody
    public CheckUserResponseDto checkUser(@Valid @RequestBody CheckUserRequestDto requestDto) {
        return new CheckUserResponseDto(!userService.isExistUserByName(requestDto.getName()));
    }

}
