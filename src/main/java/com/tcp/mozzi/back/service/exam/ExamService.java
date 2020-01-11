package com.tcp.mozzi.back.service.exam;

import com.tcp.mozzi.back.domain.exam.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> getExamList(int page, int limit);
    List<Exam> getExamByYearOrNameOrProfessor(String year, String name, String professor, int page, int limit);
    Exam getExamByExamId(int examId);
    void addExam(Exam exam);
    void updateExam(Exam exam);
    void deleteExam(Integer id);
    int getTotalExam();
}
