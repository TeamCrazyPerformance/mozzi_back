package com.tcp.mozzi.back.dto.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchExamResponseDto extends DefaultResponseDto {
    private List<Exam> exams;
    private int page;
    private int total;
}
