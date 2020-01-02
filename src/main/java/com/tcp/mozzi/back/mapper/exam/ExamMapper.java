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
    int getCount();
    void createExam(Exam exam);
    void modifyExam(Exam exam);
    List<Map<String, Object>> searchExam();
    void deleteExam();
}
