package com.tcp.mozzi.back.service.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import com.tcp.mozzi.back.mapper.exam.ExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

    private ExamMapper examMapper;

    @Autowired
    public void setExamMapper(ExamMapper examMapper){this.examMapper = examMapper;}

    @Override
    public List<Exam> getExamList(int page, int limit) {
        return examMapper.getExamList(page,limit);
    }

    @Override
    public List<Exam> getExamByYearOrNameOrProfessor(String year, String name, String professor, int page, int limit) {
        return examMapper.getExamByYearOrNameOrProfessor(year, name, professor, page, limit);
    }

    @Override
    public Exam getExamByExamId(int examId) {
        return examMapper.getExamByExamId(examId);
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
