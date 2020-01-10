package com.tcp.mozzi.back.controller.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import com.tcp.mozzi.back.dto.exam.CreateExamRequestDto;
import com.tcp.mozzi.back.dto.exam.DeleteExamRequestDto;
import com.tcp.mozzi.back.dto.exam.GetExamListResponseDto;
import com.tcp.mozzi.back.dto.exam.UpdateExamRequestDto;
import com.tcp.mozzi.back.service.exam.ExamService;
import com.tcp.mozzi.back.service.log.LogService;
import com.tcp.mozzi.back.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/exam")
@Api(tags = "Exam", description = "기출문제 (족보)")
@RestController
public class ExamController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private ExamService examService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LogService logService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "문제 보기", notes = "등록되어 있는 기출문제를 불러옵니다.")
    public ResponseEntity<?> getExamList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){

        return new ResponseEntity<>(new GetExamListResponseDto(examService.getExamList((page-1)*limit, limit), page, examService.getTotalExam()), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @ApiOperation(value = "문제 등록", notes = "기출문제를 등록합니다.")
    public ResponseEntity<?> createExam(@RequestBody CreateExamRequestDto exam){
        examService.addExam(new Exam(exam));

        return this.getExamList(1, 10);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "문제 수정", notes = "지정된 기출문제를 수정합니다.")
    public ResponseEntity<?> modifyExam(@PathVariable("id")String id, HttpServletRequest request, @RequestBody UpdateExamRequestDto updateExamDto){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader).substring(7));
        if(userId != updateExamDto.getAuthorId()) {
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.UNAUTHORIZED);
        }
        
        examService.updateExam(new Exam(updateExamDto));

        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "문제 삭제", notes = "지정된 기출문제를 삭제합니다.")
    public ResponseEntity<?> deleteExam(@PathVariable("id")String id, HttpServletRequest request, @RequestBody DeleteExamRequestDto deleteExamRequestDto){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader).substring(7));
        if(userId != deleteExamRequestDto.getAuthorId()){
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.UNAUTHORIZED);
        }

        examService.deleteExam(deleteExamRequestDto.getExamId());

        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "문제 탐색", notes = "기출문제를 탐색합니다.")
    public ResponseEntity<?> searchExam(@RequestParam("year")String year,
                                        @RequestParam("name")String name,
                                        @RequestParam("professor")String professor,
                                        @RequestParam("limit")String limit,
                                        @RequestParam("page")String page){

        return null;
    }
}
