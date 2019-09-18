package com.tcp.mozzi.back.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "storage")
@Getter
@Setter
public class FileStorageConfig {

    @Value("${file.upload-dir}")
    private String uploadDir;

}
