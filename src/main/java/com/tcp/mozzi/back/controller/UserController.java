package com.tcp.mozzi.back.controller;

import com.tcp.mozzi.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getUser(){

        List<Map<String, Object>> temp = userService.getUser();

        System.out.println(temp.get(0).get("birthday"));

        return userService.getUser();
    }

}
