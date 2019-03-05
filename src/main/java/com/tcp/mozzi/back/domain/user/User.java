package com.tcp.mozzi.back.domain.user;

import com.tcp.mozzi.back.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    private UserStatus status;
    private String nickname;
    private String studentNum;
    private String phoneNum;
    private String email;
    private Date birthday;

    public enum UserStatus {
        OK,
        WAIT
    }
}
