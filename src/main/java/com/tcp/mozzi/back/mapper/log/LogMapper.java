package com.tcp.mozzi.back.mapper.log;

import com.tcp.mozzi.back.domain.log.Log;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LogMapper {
    List<Log> selectLog();
    List<Log> selectLogByUserId(@Param("userId") int userId);
    void insertLog(Log log);
}
