package com.tcp.mozzi.back.service.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import com.tcp.mozzi.back.mapper.exam.ExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    private ExamMapper examMapper;

    @Autowired
    public void setExamMapper(ExamMapper examMapper){this.examMapper = examMapper;}

    @Override
    public List<Exam> getExamList(int page, int limit) {
        return examMapper.getExamList(page,limit);
    }

    @Override
    public void addExam(Exam exam) {
        examMapper.createExam(exam);
    }

    @Override
    public void updateExam(Exam exam) {
        examMapper.updateExam(exam);
    }

    @Override
    public void deleteExam(Integer id) {
        examMapper.deleteExam(id);
    }

    @Override
    public int getTotalExam() {
        return examMapper.getTotalExam();
    }
}
