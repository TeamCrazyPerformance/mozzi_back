package com.tcp.mozzi.back.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcp.mozzi.back.domain.user.User;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponseDto extends DefaultResponseDto {

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("student_number")
    private String student_number;

    @JsonProperty("phone_number")
    private String phone_number;

    @JsonProperty("email")
    private String email;

    @JsonProperty("birthday")
    private Date birthday;

    public UserDetailResponseDto(User user){
        this.nickname = user.getNickname();
        this.student_number = user.getStudentNum();
        this.phone_number = user.getPhoneNum();
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
    }
}
