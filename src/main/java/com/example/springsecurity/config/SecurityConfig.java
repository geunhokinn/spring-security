package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // security settings
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll() // Allow all users access to "/", "/login", "/loginProc", "/join", and "/joinProc"
                .requestMatchers("/admin").hasRole("ADMIN") // Allow user access with ADMIN role for /admin
                .requestMatchers("/my/**").hasAnyRole("ADMIN","USER") // Allow user access with ADMIN, USER role for /my/**
                .anyRequest().authenticated()); // Allow access to only logged-in users for the rest of the path

        http
                .formLogin((auth) -> auth.loginPage("/login") // Redirection to login page
                        .loginProcessingUrl("/loginProc") // Login Processing
                        .permitAll()); // Allow access to all


        http
                .csrf((auth) -> auth.disable()); // Unset csrf(cross site request forgery)

        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1) // Allow up to one duplicate login
                        .maxSessionsPreventsLogin(true)); // Block new login when exceeded

        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId()); // Change id for same session at login

        return http.build();
    }
}
