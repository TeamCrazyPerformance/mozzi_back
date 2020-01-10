package com.tcp.mozzi.back.dto.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetExamListResponseDto extends DefaultResponseDto {

    private List<Exam> exams;
    private int page;
    private int total;

}
