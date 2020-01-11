package com.tcp.mozzi.back.domain.exam;

import com.tcp.mozzi.back.dto.exam.CreateExamRequestDto;
import com.tcp.mozzi.back.dto.exam.UpdateExamRequestDto;
import lombok.*;
import org.apache.ibatis.type.MappedTypes;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Exam {

    private int examId;
    private int authorId;
    private LocalDateTime createAt;
    private LocalDateTime modifyAt;
    private String year;
    private String semester;
    private String term;
    private String name;
    private String professor;
    private int grade;
    private String major;
    private String content;

    public Exam(int author, String year, String major, int grade, String semester, String term, String name, String professor, String content){
        this.authorId = author;
        this.year = year;
        this.major = major;
        this.grade = grade;
        this.semester = semester;
        this.term = term;
        this.name = name;
        this.professor = professor;
        this.content = content;
    }
}
