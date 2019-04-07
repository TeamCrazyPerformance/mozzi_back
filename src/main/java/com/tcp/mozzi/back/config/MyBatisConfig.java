package com.tcp.mozzi.back.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.tcp.mozzi.back.mapper")
public class MyBatisConfig {
}
