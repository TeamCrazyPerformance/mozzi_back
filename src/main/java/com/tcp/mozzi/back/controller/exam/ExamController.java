package com.tcp.mozzi.back.controller.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import com.tcp.mozzi.back.dto.exam.CreateExamDto;
import com.tcp.mozzi.back.dto.exam.GetExamListDto;
import com.tcp.mozzi.back.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/exam")
@RestController
public class ExamController {

    @Autowired
    private ExamService examService;

    ExamController(ExamService examService){
        this.examService = examService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public GetExamListDto getList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(page);

        return examService.getExamList((page-1)*limit, limit);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createExam(@RequestBody CreateExamDto exam){

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyExam(){

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteExam(){

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public void searchExam(){

    }
}
