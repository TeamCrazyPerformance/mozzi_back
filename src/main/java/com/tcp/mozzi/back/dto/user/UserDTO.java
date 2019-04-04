package com.tcp.mozzi.back.dto.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User")
public class UserDTO {

    @ApiModelProperty(value = "이름", example = "NAME")
    private String name;
    @ApiModelProperty(value = "비밀번호", example = "PASSWORD")
    private String password;
    @ApiModelProperty(value = "닉네임", example = "NICKNAME")
    private String nickname;
    @ApiModelProperty(value = "학번", example = "01234567")
    private String studentNumber;
    @ApiModelProperty(value = "전화번호", example = "010-0000-0000")
    private String phoneNumber;
    @ApiModelProperty(value = "이메일", example = "guest@domain.com")
    private String email;
    @ApiModelProperty(value = "생년월일", example = "YYYY-MM-DD")
    private String birthday;

}
