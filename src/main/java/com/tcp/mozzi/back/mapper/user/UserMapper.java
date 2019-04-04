package com.tcp.mozzi.back.mapper.user;

import com.tcp.mozzi.back.dto.user.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    void createUser(UserDTO user);
}
