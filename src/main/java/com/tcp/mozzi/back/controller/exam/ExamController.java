package com.tcp.mozzi.back.controller.exam;

import com.tcp.mozzi.back.domain.exam.Exam;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import com.tcp.mozzi.back.dto.exam.CreateExamRequestDto;
import com.tcp.mozzi.back.dto.exam.GetExamListResponseDto;
import com.tcp.mozzi.back.dto.exam.SearchExamResponseDto;
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
import org.springframework.lang.Nullable;
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
    public ResponseEntity<?> createExam(HttpServletRequest request, @RequestBody CreateExamRequestDto exam){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader).substring(7));
        examService.addExam(new Exam(userId
                            ,exam.getYear()
                            ,exam.getMajor()
                            ,exam.getGrade()
                            ,exam.getSemester()
                            ,exam.getTerm()
                            ,exam.getName()
                            ,exam.getProfessor()
                            ,exam.getContent()));

        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{examId}", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "문제 수정", notes = "지정된 기출문제를 수정합니다.")
    public ResponseEntity<?> modifyExam(@PathVariable("examId")String examId, HttpServletRequest request, @RequestBody UpdateExamRequestDto updateExamDto){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader).substring(7));
        Exam exam = examService.getExamByExamId(Integer.parseInt(examId));
        if(userId != exam.getAuthorId()) {
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.UNAUTHORIZED);
        }

        examService.updateExam(Exam.builder()
                                .year(updateExamDto.getYear())
                                .major(updateExamDto.getMajor())
                                .grade(updateExamDto.getGrade())
                                .semester(updateExamDto.getSemester())
                                .term(updateExamDto.getTerm())
                                .name(updateExamDto.getName())
                                .professor(updateExamDto.getProfessor())
                                .content(updateExamDto.getContent())
                                .build());

        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{examId}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "문제 삭제", notes = "지정된 기출문제를 삭제합니다.")
    public ResponseEntity<?> deleteExam(@PathVariable("examId")String examId, HttpServletRequest request){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader).substring(7));
        Exam exam = examService.getExamByExamId(Integer.parseInt(examId));
        if(userId != exam.getAuthorId()){
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.UNAUTHORIZED);
        }

        examService.deleteExam(exam.getExamId());

        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "문제 탐색", notes = "기출문제를 탐색합니다.")
    public ResponseEntity<?> searchExam(@RequestParam(value = "year", required = false, defaultValue = "0")String year,
                                        @RequestParam(value = "name", required = false, defaultValue = "")String name,
                                        @RequestParam(value = "professor", required = false, defaultValue = "")String professor,
                                        @RequestParam(value = "page", required = false, defaultValue = "1")int page,
                                        @RequestParam(value = "limit", required = false, defaultValue = "10")int limit){

        return new ResponseEntity<>(new SearchExamResponseDto(
                examService.getExamByYearOrNameOrProfessor(year,name,professor,(page-1)*limit, limit),
                page,
                examService.getTotalExam()),
                HttpStatus.OK);
    }
}
