package com.tcp.mozzi.back.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseTimeEntity {

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
