package com.tcp.mozzi.back.controller.user;

import com.tcp.mozzi.back.dto.DefaultApiMessageDto;
import com.tcp.mozzi.back.dto.user.RegisterUserRequestDto;
import com.tcp.mozzi.back.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DefaultApiMessageDto registerUser(@Valid @RequestBody RegisterUserRequestDto userDto) throws ParseException, BindException {
        userService.addUser(userDto.toEntity());

        return new DefaultApiMessageDto();
    }

}
