package com.tcp.mozzi.back.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPasswordRequestDto {

    private String curPassword;

    private String newPassword;
}
