package com.tcp.mozzi.back.mapper.admin;

import com.tcp.mozzi.back.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    List<User> selectWaitUser(@Param("page") int page, @Param("limit") int limit);
    List<User> selectAllUser(@Param("page") int page, @Param("limit") int limit);
    int getTotalWaitUser();
    int getTotalUser();
    boolean approveUser();
}
