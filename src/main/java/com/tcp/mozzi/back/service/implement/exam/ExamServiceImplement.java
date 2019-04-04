package com.tcp.mozzi.back.service.implement.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import com.tcp.mozzi.back.dto.exam.GetExamListDto;
import com.tcp.mozzi.back.mapper.exam.ExamMapper;
import com.tcp.mozzi.back.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImplement implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    ExamServiceImplement(ExamMapper examMapper){
        this.examMapper = examMapper;
    }

    @Override
    public GetExamListDto getExamList(int page, int limit){
        List<Exam> temp = examMapper.getExamList(page, limit);

        GetExamListDto dto = new GetExamListDto();


        dto.setSuccess("true");
        dto.setExams(examMapper.getExamList(page, limit));
        dto.setTotal(examMapper.getCount());
        dto.setPage((page/limit)+1);
        dto.setCount(limit);

        return dto;
    }
}
