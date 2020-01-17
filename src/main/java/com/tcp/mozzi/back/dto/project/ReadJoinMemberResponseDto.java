package com.tcp.mozzi.back.dto.project;

import com.tcp.mozzi.back.domain.project.JoinRequestUser;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadJoinMemberResponseDto extends DefaultResponseDto {
    private List<JoinRequestUser> user;
}
