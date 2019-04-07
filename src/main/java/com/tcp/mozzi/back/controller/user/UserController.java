package com.tcp.mozzi.back.controller.user;

import com.tcp.mozzi.back.domain.user.User;
import com.tcp.mozzi.back.dto.DefaultApiMessageDto;
import com.tcp.mozzi.back.dto.user.RegisterUserDto;
import com.tcp.mozzi.back.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DefaultApiMessageDto registerUser(@RequestBody RegisterUserDto userDto) throws ParseException {
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setStatus(User.UserStatus.WAIT);
        user.setNickname(userDto.getNickname());
        user.setStudentNum(userDto.getStudentNumber());
        user.setPhoneNum(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        Date birthday = sdf.parse(userDto.getBirthday());
        user.setBirthday(birthday);
        userService.addUser(user);

        return new DefaultApiMessageDto();
    }

}
