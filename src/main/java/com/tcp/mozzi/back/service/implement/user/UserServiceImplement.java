package com.tcp.mozzi.back.service.implement.user;

import com.tcp.mozzi.back.dto.user.UserDTO;
import com.tcp.mozzi.back.mapper.user.UserMapper;
import com.tcp.mozzi.back.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    UserServiceImplement(UserMapper userMapper) {this.userMapper = userMapper;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public boolean createUser(UserDTO user) {
        final String encodePassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodePassword);
        userMapper.createUser(user);
        return true;
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
