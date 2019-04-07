package com.tcp.mozzi.back.service.user;

import com.tcp.mozzi.back.domain.user.JwtUserFactory;
import com.tcp.mozzi.back.domain.user.User;
import com.tcp.mozzi.back.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userMapper.selectUserByName(name);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with name '%s'.", name));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
