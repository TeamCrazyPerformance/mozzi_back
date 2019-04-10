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

    private Integer id;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String name;
    private String password;
    private UserStatus status;
    private String nickname;
    private String studentNum;
    private String phoneNum;
    private String email;
    private Date birthday;
    private String allow;

    public enum UserStatus {
        OK,
        WAIT
    }

    public enum UserRole {
        USER,
        ADMIN
    }

    public void updateUser(UpdateUserRequestDto updateUser){
        this.password = updateUser.getPassword();
        this.nickname = updateUser.getNickname();
        this.phoneNum = updateUser.getPhone_number();
        this.email = updateUser.getEmail();
        this.allow = updateUser.getAllow();
    }
}
