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

    @GetMapping(value = "")
    @ResponseBody
    @ApiOperation(value = "프로젝트 목록", notes = "등록되어 있는 프로젝트를 불러옵니다.")
    public ResponseEntity<?> readProject(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){

        return null;
    }


}
