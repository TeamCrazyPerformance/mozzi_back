package com.tcp.mozzi.back.mapper.example;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserMapperExample {

    List<Map<String, Object>> getUser();
}
