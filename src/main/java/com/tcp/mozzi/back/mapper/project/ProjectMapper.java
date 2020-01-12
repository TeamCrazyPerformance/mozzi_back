package com.tcp.mozzi.back.mapper.project;

import com.tcp.mozzi.back.domain.project.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {
    int getTotalProject();
    List<Project> readAllProject(@Param("page") int page, @Param("limit") int limit);
    Project readProjectByProjectId(int projectId);
    void createProject(Project project);
    void updateProject(Project project);
    void deleteProjectByProjectId(int projectId);
    void updateProjectJoinRequest(@Param("projectId") int projectId, @Param("joinRequest") String joinRequest);
}
