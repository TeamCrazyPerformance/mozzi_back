package com.tcp.mozzi.back.dto.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhoamiDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;
}
