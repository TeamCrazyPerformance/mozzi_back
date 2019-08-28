package com.tcp.mozzi.back.domain.storage;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
    private Integer storageId;
    private Integer authorId;
    private Integer location;
    private LocalDateTime createAt;
    private LocalDateTime modifyAt;
    private String contentType;
    private Boolean isFile;
    private String name;
}
