package com.tcp.mozzi.back.controller.example;

import com.tcp.mozzi.back.service.UserServiceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserControllerExample {

    @Autowired
    private UserServiceExample userServiceExample;

    UserControllerExample(UserServiceExample userServiceExample){
        this.userServiceExample = userServiceExample;
    }

    @RequestMapping(value = "/mybatis", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getUser(){

        List<Map<String, Object>> temp = userServiceExample.getUser();

        System.out.println(temp.get(0).get("birthday"));

        return userServiceExample.getUser();
    }

}
