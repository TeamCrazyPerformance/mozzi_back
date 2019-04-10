package com.tcp.mozzi.back.controller.user;

import com.tcp.mozzi.back.domain.user.JwtUser;
import com.tcp.mozzi.back.dto.user.LoginRequestDto;
import com.tcp.mozzi.back.dto.user.UserTokenResponseDto;
import com.tcp.mozzi.back.exception.AuthenticationException;
import com.tcp.mozzi.back.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Api(tags = "Authentication", description = "토큰 생성 및 Refresh")
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
    @ApiOperation(value = "로그인", notes = "아이디와 비밀번호로 토큰을 발급받습니다.")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequestDto loginRequestDto) throws AuthenticationException {
        authenticate(loginRequestDto.getName(), loginRequestDto.getPassword());

        final JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(loginRequestDto.getName());
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println(jwtTokenUtil.getUsernameFromToken(token));

        return new ResponseEntity<>(new UserTokenResponseDto(token, jwtTokenUtil.getRoleFromToken(token)), HttpStatus.OK);
    }

    @GetMapping("/user/refresh")
    @ResponseBody
    @ApiOperation(value = "토큰 재발급", notes = "기간이 만료된 토큰을 재발급 받습니다.")
    public UserTokenResponseDto refreshAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastModified())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return new UserTokenResponseDto(refreshedToken, jwtTokenUtil.getRoleFromToken(refreshedToken));
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
