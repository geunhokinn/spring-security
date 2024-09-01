package com.example.springsecurity.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 아이디
    @Column(nullable = false, unique = true)
    private String username;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 사용자의 권한을 나타내기위한 role
    @Column(nullable = false)
    private String role;

    @Builder
    private Member(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static Member buildMember(String username, String password, String role) {
        return Member.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();
    }
}

