package com.tcp.mozzi.back.domain.user;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(),
                user.getRole().toString(),
                user.getName(),
                user.getPassword(),
                user.getStatus().equals(User.UserStatus.OK),
                new ArrayList<>(), //TODO: mapToGrantedAuthorities(user.getAuthorities())
                user.getCreatedAt(),
                user.getModifiedAt());
    }

//    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
//        return authorities.stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
//                .collect(Collectors.toList());
//    }

}
