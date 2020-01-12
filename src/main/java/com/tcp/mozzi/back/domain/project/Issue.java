package com.tcp.mozzi.back.domain.project;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Issue {

    private int issueId;
    private int projectId;
    private int authorId;
    private LocalDateTime createAt;
    private LocalDateTime modifyAt;
    private String title;
    private String content;
}
