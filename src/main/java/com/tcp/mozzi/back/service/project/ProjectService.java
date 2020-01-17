package com.tcp.mozzi.back.service.project;

import com.tcp.mozzi.back.domain.project.JoinRequestUser;
import com.tcp.mozzi.back.domain.project.Project;
import com.tcp.mozzi.back.domain.user.User;

import java.util.List;

public interface ProjectService {
    int getTotalProject();
    List<Project> readAllProject(int page, int limit);
    Project readProjectByProjectId(int projectId);
    void createProject(Project project);
    void updateProject(Project project);
    void deleteProjectByProjectId(int projectId);
    boolean joinProject(Project project, int userId);
    List<JoinRequestUser> readJoinRequest(String joinRequest);
}
