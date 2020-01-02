package com.tcp.mozzi.back.controller.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import com.tcp.mozzi.back.dto.exam.CreateExamDto;
import com.tcp.mozzi.back.dto.exam.GetExamListDto;
import com.tcp.mozzi.back.service.exam.ExamService;
import com.tcp.mozzi.back.service.log.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.reader.StreamReader;

@RequestMapping("/exam")
@Api(tags = "Exam", description = "기출문제 (족보)")
@RestController
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private LogService logService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "문제 보기", notes = "등록되어 있는 기출문제를 불러옵니다.")
    public ResponseEntity<?> getExamList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){

        return new ResponseEntity<>(new GetExamListDto(examService.getExamList(page, limit)), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @ApiOperation(value = "문제 등록", notes = "기출문제를 등록합니다.")
    public ResponseEntity<?> createExam(@RequestBody CreateExamDto exam){
        examService.addExam(new Exam(exam));

        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.CREATED);
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
