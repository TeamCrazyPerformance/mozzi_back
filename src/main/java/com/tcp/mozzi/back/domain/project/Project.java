package com.tcp.mozzi.back.domain.project;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    private int projectId;
    private int authorId;
    private LocalDateTime createAt;
    private LocalDateTime modifyAt;
    private String title;
    private String content;
    private String qualification;
    private String member;
    private String joinRequest;
}
