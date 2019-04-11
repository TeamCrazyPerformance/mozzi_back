package com.tcp.mozzi.back.mapper.admin;

import com.tcp.mozzi.back.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    List<User> selectWaitUsers(@Param("page") int page, @Param("limit") int limit);
    int getWaitTotal();
    boolean approveUser();
}
