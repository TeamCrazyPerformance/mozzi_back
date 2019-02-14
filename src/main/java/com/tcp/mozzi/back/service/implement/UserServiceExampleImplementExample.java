package com.tcp.mozzi.back.service.implement;

import com.tcp.mozzi.back.mapper.example.UserMapperExample;
import com.tcp.mozzi.back.service.UserServiceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceExampleImplementExample implements UserServiceExample {

    @Autowired
    private UserMapperExample userMapperExample;


    UserServiceExampleImplementExample(UserMapperExample userMapperExample){
        this.userMapperExample = userMapperExample;
    }

    public List<Map<String, Object>> getUser(){
        return userMapperExample.getUser();
    }
}
