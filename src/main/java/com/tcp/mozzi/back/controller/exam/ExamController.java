package com.tcp.mozzi.back.controller.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import com.tcp.mozzi.back.dto.exam.CreateExamDto;
import com.tcp.mozzi.back.dto.exam.GetExamListDto;
import com.tcp.mozzi.back.service.exam.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.reader.StreamReader;

@RequestMapping("/exam")
@Api(tags = "Exam", description = "기출문제 (족보)")
@RestController
public class ExamController {

    @Autowired
    private ExamService examService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "문제 보기", notes = "등록되어 있는 기출문제를 불러옵니다.")
    public GetExamListDto getList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(page);

        return examService.getExamList((page-1)*limit, limit);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "문제 등록", notes = "기출문제를 등록합니다.")
    public void createExam(@RequestBody CreateExamDto exam){

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "문제 수정", notes = "지정된 기출문제를 수정합니다.")
    public void modifyExam(){

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "문제 삭제", notes = "지정된 기출문제를 삭제합니다.")
    public void deleteExam(){

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "문제 탐색", notes = "기출문제를 탐색합니다.")
    public void searchExam(){

    }
}
