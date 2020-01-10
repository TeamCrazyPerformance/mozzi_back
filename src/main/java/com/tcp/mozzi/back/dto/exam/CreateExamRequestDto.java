package com.tcp.mozzi.back.dto.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateExamRequestDto {
    private String year;
    private String major;
    private int grade;
    private int semester;
    private String term;
    private String name;
    private String professor;
    private String content;

}
