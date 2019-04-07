package com.tcp.mozzi.back;

import com.tcp.mozzi.back.config.JwtFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.tcp.mozzi.back.mapper")
public class BackApplication {

    @Bean
    public FilterRegistrationBean jwtFilter(){

        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/ttt/*");
        registrationBean.addUrlPatterns("/ttest/*");

        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

}

