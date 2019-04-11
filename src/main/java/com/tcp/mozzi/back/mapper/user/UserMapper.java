package com.tcp.mozzi.back.mapper.user;

import com.tcp.mozzi.back.domain.user.User;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    User selectUserById(Integer id);
    User selectUserByName(String name);
    List<User> selectUsers();
    void insertUser(User user);
    void updateUser(User user);
    void deleteUserById(Integer id);
    boolean isExistUserByName(String name);
}
