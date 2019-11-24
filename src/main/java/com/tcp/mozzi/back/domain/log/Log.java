package com.tcp.mozzi.back.domain.log;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private Integer logId;
    private Integer authorId;
    private LocalDateTime createAt;
    private String controller;
    private String httpMethod;
    private String method;
    private String request;
}
