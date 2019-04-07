package com.tcp.mozzi.back.controller.user;

import com.tcp.mozzi.back.domain.user.JwtUser;
import com.tcp.mozzi.back.dto.user.LoginRequestDto;
import com.tcp.mozzi.back.dto.user.UserTokenResponseDto;
import com.tcp.mozzi.back.exception.AuthenticationException;
import com.tcp.mozzi.back.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @PostMapping("/user/login")
    @ResponseBody
    public UserTokenResponseDto createAuthenticationToken(@RequestBody LoginRequestDto loginRequestDto) throws AuthenticationException {
        authenticate(loginRequestDto.getName(), loginRequestDto.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getName());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return new UserTokenResponseDto(token);
    }

    @GetMapping("/user/refresh")
    @ResponseBody
    public UserTokenResponseDto refreshAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastModified())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return new UserTokenResponseDto(refreshedToken);
        } else {
            //TODO bad request
            return null;
        }
    }

    private void authenticate(String name, String password) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }

}
