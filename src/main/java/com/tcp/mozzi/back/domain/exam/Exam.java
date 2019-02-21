package com.tcp.mozzi.back.domain.exam;

import com.tcp.mozzi.back.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Exam extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exam_id;
    private int author;
    private String year;
    private String major;
    private int grade;
    private int semester;
    private String term;
    private String name;
    private String professor;
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String content;

    @Builder
    public Exam(int author, String year, String major, int grade, int semester, String term, String name, String professor, String content){
        this.author = author;
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
