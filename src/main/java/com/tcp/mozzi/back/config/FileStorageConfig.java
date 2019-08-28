package com.tcp.mozzi.back.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "storage")
@Getter
@Setter
public class FileStorageConfig {
    private String uploadDir;

}
