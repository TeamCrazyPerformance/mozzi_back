package com.tcp.mozzi.back.mapper.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ExamMapper {

    int getTotalExam();
    Exam getExamByExamId(int examId);
    List<Exam> getExamList(@Param("page") int page, @Param("limit") int limit);
    List<Exam> getExamByYearOrNameOrProfessor(@Param("year") String year,
                                              @Param("name") String name,
                                              @Param("professor") String professor,
                                              @Param("page") int page,
                                              @Param("limit") int limit);
    void createExam(Exam exam);
    void updateExam(Exam exam);
    void deleteExam(int examId);
}
