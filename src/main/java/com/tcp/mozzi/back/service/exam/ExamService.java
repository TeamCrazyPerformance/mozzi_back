package com.tcp.mozzi.back.service.exam;

import com.tcp.mozzi.back.dto.exam.GetExamListDto;

public interface ExamService {
    GetExamListDto getExamList(int page, int limit);
}
