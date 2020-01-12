package com.tcp.mozzi.back.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateProjectRequestDto {
    private String title;
    private String content;
    private String qualification;
}
