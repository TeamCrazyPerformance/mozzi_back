package com.tcp.mozzi.back.domain.project;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IssueComment {

    private int issueCommentId;
    private int issueId;
    private int authorId;
    private LocalDateTime createAt;
    private LocalDateTime modifyAt;
    private String content;
}
