package com.tcp.mozzi.back.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcp.mozzi.back.domain.user.User;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponseDto extends DefaultResponseDto {

    private User.UserRole role;
    private String id;
    private String name;
    private String nickname;
    private String school;
    private String major;
    private String studentNumber;
    private String phoneNumber;
    private String email;
    private Date birthday;
    private LocalDateTime createAt;
    private String allow;

    public UserDetailResponseDto(User user){
        this.role = user.getRole();
        this.id = user.getId();
        this.name = user.getName();
        this.school = user.getSchool();
        this.major = user.getMajor();
        this.nickname = user.getNickname();
        this.studentNumber = user.getStudentNum();
        this.phoneNumber = user.getPhoneNum();
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
        this.createAt = user.getCreatedAt();
        this.allow = user.getAllow();
    }
}
