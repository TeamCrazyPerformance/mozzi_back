package com.tcp.mozzi.back.service.implement;

import com.tcp.mozzi.back.mapper.UserMapper;
import com.tcp.mozzi.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserMapper userMapper;


    UserServiceImplement(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public List<Map<String, Object>> getUser(){
        return userMapper.getUser();
    }
}
