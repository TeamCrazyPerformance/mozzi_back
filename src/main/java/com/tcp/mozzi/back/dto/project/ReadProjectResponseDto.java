package com.tcp.mozzi.back.dto.project;

import com.tcp.mozzi.back.domain.project.Project;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadProjectResponseDto extends DefaultResponseDto {
    private List<Project> projects;
    private int page;
    private int total;
}
