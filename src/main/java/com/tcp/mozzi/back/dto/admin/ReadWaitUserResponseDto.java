package com.tcp.mozzi.back.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcp.mozzi.back.domain.user.User;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadWaitUserResponseDto extends DefaultResponseDto {

    @JsonProperty("users")
    private List<User> users;

    @JsonProperty("page")
    private int page;

    @JsonProperty("total")
    private int total;
}
