package com.tcp.mozzi.back.service.user;

import com.tcp.mozzi.back.dto.user.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService extends UserDetailsService {
    boolean createUser(UserDTO user);
    PasswordEncoder passwordEncoder();
}
