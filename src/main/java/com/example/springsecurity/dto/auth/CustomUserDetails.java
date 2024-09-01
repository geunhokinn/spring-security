package com.example.springsecurity.dto.auth;

import com.example.springsecurity.dto.InfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final InfoDTO infoDTO;

    // 사용자 권한 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return infoDTO.getRole();
            }
        });

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return infoDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return infoDTO.getUsername();
    }

    // 사용자의 아이디가 만료되었는지
    @Override
    public boolean isAccountNonExpired() {
        // 만료되지 않음
        return true;
    }

    // 사용자의 아이디가 잠겨있는지
    @Override
    public boolean isAccountNonLocked() {
        // 잠겨있지 않음
        return true;
    }

    // 사용자의 자격 증명이 만료되었는지
    @Override
    public boolean isCredentialsNonExpired() {
        // 만료되지 않음
        return true;
    }

    // 사용자의 아이디가 사용 가능한지
    @Override
    public boolean isEnabled() {
        // 사용 가능
        return true;
    }
}
