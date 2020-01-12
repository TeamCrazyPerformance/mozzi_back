package com.tcp.mozzi.back.controller.project;


import com.tcp.mozzi.back.service.log.LogService;
import com.tcp.mozzi.back.service.project.ProjectService;
import com.tcp.mozzi.back.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;

@RequestMapping("/project")
@RestController
@Api(tags = "Project", description = "프로젝트")
public class ProjectController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LogService logService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    @ResponseBody
    @ApiOperation(value = "프로젝트 목록", notes = "등록되어 있는 프로젝트를 불러옵니다.")
    public ResponseEntity<?> readProject(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){

        return null;
    }

    @GetMapping("/{projectId}")
    @ResponseBody
    @ApiOperation(value = "프로젝트 보기", notes = "지정한 프로젝트를 불러옵니다.")
    public ResponseEntity<?> readProjectDetail(@PathVariable("id") String id){

        return null;
    }

    @PostMapping("")
    @ResponseBody
    @ApiOperation(value = "프로젝트 생성", notes = "프로젝트를 생성합니다.")
    public ResponseEntity<?> createProject(){

        return null;
    }

    @PutMapping("")
    @ResponseBody
    @ApiOperation(value = "프로젝트 수정", notes = "프로젝트를 수정합니다.")
    public ResponseEntity<?> updateProject(){

        return null;
    }

    @DeleteMapping("/{projectId}")
    @ResponseBody
    @ApiOperation(value = "프로젝트 삭제", notes = "프로젝트를 삭제합니다.")
    public ResponseEntity<?> deleteProject(){

        return null;
    }

    @PostMapping("/join")
    @ResponseBody
    @ApiOperation(value = "프로젝트 가입신청", notes = "지정한 프로젝트에 가입신청 합니다.")
    public ResponseEntity<?> joinProject(){

        return null;
    }

    @GetMapping("/join/{projectId}")
    @ResponseBody
    @ApiOperation(value = "프로젝트 신청멤버 보기", notes = "프로젝트에 가입신청 한 멤버를 불러옵니다.")
    public ResponseEntity<?> readJoinMember(){

        return null;
    }

    @PutMapping("/{projectId}/approve/{userId}")
    @ResponseBody
    @ApiOperation(value = "프로젝트 가입승인", notes = "프로젝트 가입을 승인합니다.")
    public ResponseEntity<?> approveJoinProject(){

        return null;
    }

    @PutMapping("/{projectId}/reject/{userId}")
    @ResponseBody
    @ApiOperation(value = "프로젝트 가입거절", notes = "프로젝트 가입을 거절합니다.")
    public ResponseEntity<?> rejectJoinProject(){

        return null;
    }

    @DeleteMapping("/{projectId}/exit")
    @ResponseBody
    @ApiOperation(value = "프로젝트 탈퇴", notes = "지정한 프로젝트에서 탈퇴합니다.")
    public ResponseEntity<?> exitProject(){

        return null;
    }

}
