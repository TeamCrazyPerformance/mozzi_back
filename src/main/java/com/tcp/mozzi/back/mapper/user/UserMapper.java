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
    User selectUserByUserId(Integer userId);
    User selectUserById(String id);
    List<User> selectUsers();
    void insertUser(User user);
    void updateUser(User user);
    void updateUserPassword(@Param("userId")Integer userId, @Param("newPassword")String newPassword);
    void deleteUserById(Integer id);
    boolean isValidUser(@Param("userId")Integer userId, @Param("curPassword")String curPassword);
    boolean isExistUserByName(String name);
}
