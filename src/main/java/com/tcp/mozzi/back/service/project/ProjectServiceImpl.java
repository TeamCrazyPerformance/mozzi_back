package com.tcp.mozzi.back.service.project;

import com.tcp.mozzi.back.domain.project.JoinRequestUser;
import com.tcp.mozzi.back.domain.project.Project;
import com.tcp.mozzi.back.domain.user.User;
import com.tcp.mozzi.back.mapper.project.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private ProjectMapper projectMapper;

    @Autowired
    public void setProjectMapper(ProjectMapper projectMapper){this.projectMapper = projectMapper;}

    @Override
    public int getTotalProject() {
        return projectMapper.getTotalProject();
    }

    @Override
    public List<Project> readAllProject(int page, int limit) {
        return projectMapper.readAllProject(page, limit);
    }

    @Override
    public Project readProjectByProjectId(int projectId) {
        return projectMapper.readProjectByProjectId(projectId);
    }

    @Override
    public void createProject(Project project) {
        projectMapper.createProject(project);
    }

    @Override
    public void updateProject(Project project) {
        projectMapper.updateProject(project);
    }

    @Override
    public void deleteProjectByProjectId(int projectId) {
        projectMapper.deleteProjectByProjectId(projectId);
    }

    @Override
    public boolean joinProject(Project project, int userId) {
        StringTokenizer joinMember = new StringTokenizer(project.getJoinRequest(), ",");
        while(joinMember.hasMoreTokens()){
            if(joinMember.nextToken().equals(Integer.toString(userId))){
                return false;
            }
        }
        projectMapper.updateProjectJoinRequest(project.getProjectId(),
                project.getJoinRequest()
                .concat(",")
                .concat(Integer.toString(userId)));

        return true;
    }

    @Override
    public List<JoinRequestUser> readJoinRequest(String joinRequest) {
        List<JoinRequestUser> returnContainer = new ArrayList<>();
        StringTokenizer joinMember = new StringTokenizer(joinRequest, ",");
        while(joinMember.hasMoreTokens()){

        }

        
        return null;
    }

}
