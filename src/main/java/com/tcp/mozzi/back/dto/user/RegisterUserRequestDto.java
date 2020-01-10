package com.tcp.mozzi.back.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcp.mozzi.back.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequestDto {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @NotBlank(message = "'userId' is required.")
    @JsonProperty("id")
    private String id;

    @NotBlank(message = "'password' is required.")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "'nickname' is required.")
    @JsonProperty("nickname")
    private String nickname;

    @NotBlank(message = "'name' is required.")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "'studentNumber' is required.")
    @JsonProperty("studentNumber")
    private String studentNumber;

    @NotBlank(message ="'major' is required.")
    @JsonProperty("major")
    private String major;

    @NotBlank(message = "'phoneNumber' is required.")
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @NotBlank(message = "'email' is required.")
    @Email(message = "Invalid email.")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "'birthday' is required.")
    @JsonProperty("birthday")
    private String birthday;

    @NotBlank(message = "'school' is required.")
    @JsonProperty("school")
    private String school;

    public User toEntity() {
        User user = new User();
        user.setId(this.getId());
        user.setPassword(this.getPassword());
        user.setStatus(User.UserStatus.wait);
        user.setNickname(this.getNickname());
        user.setName(this.getName());
        user.setStudentNum(this.getStudentNumber());
        user.setMajor(this.getMajor());
        user.setPhoneNum(this.getPhoneNumber());
        user.setEmail(this.getEmail());
        Date birthday = null;
        try {
            birthday = sdf.parse(this.getBirthday());
        } catch (ParseException ignore) {

        }
        user.setBirthday(birthday);
        user.setSchool(this.getSchool());
        return user;
    }
}
