package com.tcp.mozzi.back.domain.user;

import com.tcp.mozzi.back.dto.user.UpdateUserRequestDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer userId;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String id;
    private String password;
    private String name;
    private UserStatus status;
    private String nickname;
    private String studentNum;
    private String major;
    private String phoneNum;
    private String email;
    private Date birthday;
    private String allow;

    public enum UserStatus {
        wait,
        authorized
    }

    public enum UserRole {
        user,
        admin
    }

    public void updateUser(UpdateUserRequestDto updateUser){
        this.password = updateUser.getPassword();
        this.nickname = updateUser.getNickname();
        this.phoneNum = updateUser.getPhone_number();
        this.email = updateUser.getEmail();
        this.allow = updateUser.getAllow();
    }
}
