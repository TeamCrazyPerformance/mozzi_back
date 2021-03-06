package com.tcp.mozzi.back.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @JsonProperty("id")
    private String name;

    @JsonProperty("password")
    private String password;
}
