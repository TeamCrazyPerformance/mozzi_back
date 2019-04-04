package com.tcp.mozzi.back.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
    @ApiModelProperty(value = "성공 여부")
    private boolean success;
}
