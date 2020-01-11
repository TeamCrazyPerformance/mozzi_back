package com.tcp.mozzi.back.domain.user;

import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(user.getUserId(),
                user.getRole().toString(),
                user.getId(),
                user.getPassword(),
                user.getStatus().equals(User.UserStatus.authorized),
                new ArrayList<>(), //TODO: mapToGrantedAuthorities(user.getAuthorities())
                user.getCreateAt(),
                user.getModifyAt());
    }

//    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
//        return authorities.stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getUserId().userId()))
//                .collect(Collectors.toList());
//    }

}
