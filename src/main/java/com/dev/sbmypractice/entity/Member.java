package com.dev.sbmypractice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) { this.password = password; }

    @Builder
    public Member(Long id, String email, String password, String nickname, Authority authority) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
    }
}