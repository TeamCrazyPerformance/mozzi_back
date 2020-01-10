package com.tcp.mozzi.back.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("phoneNumber")
    private String phone_number;

    @JsonProperty("email")
    private String email;

    @JsonProperty("allow")
    private String allow;
}
