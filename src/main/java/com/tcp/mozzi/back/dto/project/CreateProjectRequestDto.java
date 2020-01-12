package com.tcp.mozzi.back.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CreateProjectRequestDto {
    private String title;
    private String content;
    private String qualification;
}
