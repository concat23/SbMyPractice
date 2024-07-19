package com.dev.sbmypractice.service;

import com.dev.sbmypractice.entity.Member;
import com.dev.sbmypractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    // Load user details by user ID
    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        return memberRepository.findById(id)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with ID " + id + " in the database"));
    }

    // Load user details by username (email)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with username " + username + " in the database"));
    }

    // Create UserDetails from Member entity
    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());

        return new User(
                String.valueOf(member.getId()), // Username (used as ID here)
                member.getPassword(), // User's password
                Collections.singleton(grantedAuthority) // User's authorities
        );
    }
}
