package com.tcp.mozzi.back.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenResponseDto extends DefaultResponseDto {

    @JsonProperty("token")
    private String token;
}
