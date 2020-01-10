package com.tcp.mozzi.back.mapper.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ExamMapper {

    List<Exam> getExamList(@Param("page") int page, @Param("limit") int limit);
    int getTotalExam();
    void createExam(Exam exam);
    void updateExam(Exam exam);
    List<Exam> searchExam();
    void deleteExam(int id);
}
