package com.tcp.mozzi.back.dto.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateExamDto {
    private int examId;
    private int authorId;
    private String year;
    private String major;
    private int grade;
    private int semester;
    private String term;
    private String name;
    private String professor;
    private String content;

}
