package com.tcp.mozzi.back.domain.user;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String password;
    private UserStatus status;
    private String nickname;
    private String studentNum;
    private String phoneNum;
    private String email;
    private Date birthday;

    public enum UserStatus {
        OK,
        WAIT
    }
}
