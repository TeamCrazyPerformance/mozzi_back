package com.tcp.mozzi.back.domain.project;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequestUser {
    private int userId;
    private String nickname;
    private String studentNumber;
}
