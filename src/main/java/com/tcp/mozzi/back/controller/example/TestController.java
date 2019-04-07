package com.tcp.mozzi.back.controller.example;

import com.tcp.mozzi.back.domain.user.JwtUser;
import com.tcp.mozzi.back.domain.user.User;
import com.tcp.mozzi.back.dto.test.WhoamiDto;
import com.tcp.mozzi.back.dto.user.UserTokenResponseDto;
import com.tcp.mozzi.back.service.user.UserService;
import com.tcp.mozzi.back.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/test")
@RestController
public class TestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/whoami")
    @ResponseBody
    public WhoamiDto refreshAuthenticationToken(HttpServletRequest request) {
        final String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);
        User user = userService.getUserById(jwtUser.getId());

        return new WhoamiDto(user.getName(), user.getEmail());
    }

}
