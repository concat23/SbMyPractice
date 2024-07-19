package com.dev.sbmypractice.dto;

import com.dev.sbmypractice.entity.Authority;
import com.dev.sbmypractice.entity.Member;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {
    @NotNull
    private String email;
    private String password;
    private String nickname;

    public Member toMember(PasswordEncoder passwordEncoder) {
        System.out.println("Converting to Member entity");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Nickname: " + nickname);
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .authority(Authority.ROLE_USER)
                .build();
    }


    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
