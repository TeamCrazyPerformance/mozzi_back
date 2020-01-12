package com.tcp.mozzi.back.dto.project;

import com.tcp.mozzi.back.domain.project.Project;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadProjectDetailResponseDto extends DefaultResponseDto {
    private Project project;
}
