package com.tcp.mozzi.back.dto.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateExamDto {
    private String year;
    private String major;
    private int grade;
    private int semester;
    private String term;
    private String name;
    private String professor;
    private String content;

    public Exam toEntity(){
        return Exam.builder()
                .year(year)
                .major(major)
                .grade(grade)
                .semester(semester)
                .term(term)
                .name(name)
                .professor(professor)
                .content(content)
                .build();
    }
}
