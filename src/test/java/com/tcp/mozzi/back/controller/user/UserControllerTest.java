package com.tcp.mozzi.back.controller.user;

import com.tcp.mozzi.back.dto.user.RegisterUserRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private UserController userController;

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @Test
    public void registerUserOne() throws ParseException {
        RegisterUserRequestDto dto = new RegisterUserRequestDto();
        dto.setName("김현욱");
        dto.setPassword("password");
        dto.setNickname("KUvH");
        dto.setStudentNumber("16100000");
        dto.setPhoneNumber("010-1234-5678");
        dto.setEmail("kuvh@live.co.kr");
        dto.setBirthday("1997-02-01");

        userController.registerUser(dto);
    }
}
