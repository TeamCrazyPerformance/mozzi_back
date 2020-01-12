package com.tcp.mozzi.back.controller.project;

import com.tcp.mozzi.back.service.log.LogService;
import com.tcp.mozzi.back.service.project.IssueService;
import com.tcp.mozzi.back.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/project")
@RestController
@Api(tags = "Issue", description = "프로젝트 이슈")
public class IssueController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IssueService issueService;

    @Autowired
    private LogService logService;

    @GetMapping("/{projectId}/issue")
    @ResponseBody
    @ApiOperation(value = "이슈 보기", notes = "해당 프로젝트의 이슈를 불러옵니다.")
    public ResponseEntity<?> readProjectIssue(){

        return null;
    }

    @GetMapping("/{projectId}/issue/{issueId}")
    @ResponseBody
    @ApiOperation(value = "이슈 상세보기", notes = "해당 프로젝트의 지정한 이슈를 불러옵니다.")
    public ResponseEntity<?> readProjectIssueDetail(){

        return null;
    }

    @PostMapping("/{projectId}/issue")
    @ResponseBody
    @ApiOperation(value = "이슈 생성", notes = "이슈를 생성합니다.")
    public ResponseEntity<?> createProjectIssue(){

        return null;
    }

    @DeleteMapping("/{projectId}/issue/{issueId}")
    @ResponseBody
    @ApiOperation(value = "이슈 삭제", notes = "지정된 이슈를 삭제합니다.")
    public ResponseEntity<?> deleteProjectIssue(){

        return null;
    }

    @PutMapping("/{projectId}/issue/{issueId}")
    @ResponseBody
    @ApiOperation(value = "이슈 수정", notes = "지정된 이슈를 수정합니다.")
    public ResponseEntity<?> updateProjectIssue(){

        return null;
    }

    @PostMapping("/{projectId}/issue/{issueId}")
    @ResponseBody
    @ApiOperation(value = "이슈 코멘트 생성", notes = "지정된 이슈에 코멘트를 생성합니다.")
    public ResponseEntity<?> createProjectIssueComment(){

        return null;
    }
}
