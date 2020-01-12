package com.tcp.mozzi.back.controller.project;


import com.tcp.mozzi.back.domain.project.Project;
import com.tcp.mozzi.back.dto.DefaultResponseDto;
import com.tcp.mozzi.back.dto.project.CreateProjectRequestDto;
import com.tcp.mozzi.back.dto.project.ReadProjectDetailResponseDto;
import com.tcp.mozzi.back.dto.project.ReadProjectResponseDto;
import com.tcp.mozzi.back.dto.project.UpdateProjectRequestDto;
import com.tcp.mozzi.back.service.log.LogService;
import com.tcp.mozzi.back.service.project.ProjectService;
import com.tcp.mozzi.back.service.user.UserService;
import com.tcp.mozzi.back.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserService userService;

    @GetMapping("")
    @ResponseBody
    @ApiOperation(value = "프로젝트 목록", notes = "등록되어 있는 프로젝트를 불러옵니다.")
    public ResponseEntity<?> readAllProject(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){

        return new ResponseEntity<>(new ReadProjectResponseDto(projectService.readAllProject((page-1)*limit, limit), page, projectService.getTotalProject()), HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    @ResponseBody
    @ApiOperation(value = "프로젝트 보기", notes = "지정한 프로젝트를 불러옵니다.")
    public ResponseEntity<?> readProjectDetail(@PathVariable("projectId") int projectId){

        return new ResponseEntity<>(new ReadProjectDetailResponseDto(projectService.readProjectByProjectId(projectId)), HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseBody
    @ApiOperation(value = "프로젝트 생성", notes = "프로젝트를 생성합니다.")
    public ResponseEntity<?> createProject(HttpServletRequest request, @RequestBody CreateProjectRequestDto createProjectRequestDto){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader).substring(7));
        projectService.createProject(Project.builder()
                                        .authorId(userId)
                                        .title(createProjectRequestDto.getTitle())
                                        .content(createProjectRequestDto.getContent())
                                        .qualification(createProjectRequestDto.getQualification())
                                        .member(Integer.toString(userId))
                                        .build());


        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.CREATED);
    }

    @PutMapping("/{projectId}")
    @ResponseBody
    @ApiOperation(value = "프로젝트 수정", notes = "프로젝트를 수정합니다.")
    public ResponseEntity<?> updateProject(HttpServletRequest request, @PathVariable("projectId") int projectId, @RequestBody UpdateProjectRequestDto updateProjectRequestDto){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader).substring(7));
        Project project = projectService.readProjectByProjectId(projectId);
        if(userId != project.getAuthorId()){
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.UNAUTHORIZED);
        }
        projectService.updateProject(Project.builder()
                                        .title(updateProjectRequestDto.getTitle())
                                        .content(updateProjectRequestDto.getContent())
                                        .qualification(updateProjectRequestDto.getQualification())
                                        .build());

        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    @ResponseBody
    @ApiOperation(value = "프로젝트 삭제", notes = "프로젝트를 삭제합니다.")
    public ResponseEntity<?> deleteProject(HttpServletRequest request, @PathVariable("projectId") int projectId){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader).substring(7));
        Project project = projectService.readProjectByProjectId(projectId);
        if(userId != project.getAuthorId()){
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.UNAUTHORIZED);
        }
        projectService.deleteProjectByProjectId(projectId);

        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.OK);
    }

    @PostMapping("/join/{projectId}")
    @ResponseBody
    @ApiOperation(value = "프로젝트 가입신청", notes = "지정한 프로젝트에 가입신청 합니다.")
    public ResponseEntity<?> joinProject(HttpServletRequest request, @PathVariable("projectId") int projectId){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader).substring(7));
        Project project = projectService.readProjectByProjectId(projectId);
        if(!projectService.joinProject(project, userId)){
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new DefaultResponseDto(), HttpStatus.OK);
    }

    @GetMapping("/join/{projectId}")
    @ResponseBody
    @ApiOperation(value = "프로젝트 신청멤버 보기", notes = "프로젝트에 가입신청 한 멤버를 불러옵니다.")
    public ResponseEntity<?> readJoinMember(HttpServletRequest request, @PathVariable("projectId") int projectId){
        int userId = jwtTokenUtil.getIdFromToken(request.getHeader(tokenHeader).substring(7));
        Project project = projectService.readProjectByProjectId(projectId);
        if(userId != project.getAuthorId()){
            return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.OK);
    }

    @PutMapping("/{projectId}/approve/{userId}")
    @ResponseBody
    @ApiOperation(value = "프로젝트 가입승인", notes = "프로젝트 가입을 승인합니다.")
    public ResponseEntity<?> approveJoinProject(){

        return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.OK);
    }

    @PutMapping("/{projectId}/reject/{userId}")
    @ResponseBody
    @ApiOperation(value = "프로젝트 가입거절", notes = "프로젝트 가입을 거절합니다.")
    public ResponseEntity<?> rejectJoinProject(){

        return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}/exit")
    @ResponseBody
    @ApiOperation(value = "프로젝트 탈퇴", notes = "지정한 프로젝트에서 탈퇴합니다.")
    public ResponseEntity<?> exitProject(){

        return new ResponseEntity<>(new DefaultResponseDto(false), HttpStatus.OK);
    }

}
