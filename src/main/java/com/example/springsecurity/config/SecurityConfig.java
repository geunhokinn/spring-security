package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // security 설정
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login").permitAll() // /, /login 에 대해 모두 접근 허용
                .requestMatchers("/admin").hasRole("ADMIN") // /admin 에 대해 ADMIN role 을 가져야 접근 허용
                .requestMatchers("/my/**").hasAnyRole("ADMIN","USER") // /my/** 에 대해 ADMIN, USER role 을 가져야 접근 허용
                .anyRequest().authenticated()) // 나머지 경로에 대해 로그인한 사용자만 접근 허용
                .build();
    }
}
