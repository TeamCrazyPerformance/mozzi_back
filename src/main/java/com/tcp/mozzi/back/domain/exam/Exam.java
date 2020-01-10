package com.tcp.mozzi.back.domain.exam;

import com.tcp.mozzi.back.dto.exam.CreateExamRequestDto;
import com.tcp.mozzi.back.dto.exam.UpdateExamRequestDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class Exam {

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

    @Builder
    public Exam(int author, String year, String major, int grade, int semester, String term, String name, String professor, String content){
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

    public Exam(CreateExamRequestDto createExamRequestDto){
        this.year = createExamRequestDto.getYear();
        this.major = createExamRequestDto.getMajor();
        this.grade = createExamRequestDto.getGrade();
        this.semester = createExamRequestDto.getSemester();
        this.term = createExamRequestDto.getTerm();
        this.name = createExamRequestDto.getName();
        this.professor = createExamRequestDto.getProfessor();
        this.content = createExamRequestDto.getContent();
    }

    public Exam(UpdateExamRequestDto updateExamDto){
        this.examId = updateExamDto.getExamId();
        this.authorId = updateExamDto.getAuthorId();
        this.year = updateExamDto.getYear();
        this.major = updateExamDto.getMajor();
        this.grade = updateExamDto.getGrade();
        this.semester = updateExamDto.getSemester();
        this.term = updateExamDto.getTerm();
        this.name = updateExamDto.getName();
        this.professor = updateExamDto.getProfessor();
        this.content = updateExamDto.getContent();
    }
}
