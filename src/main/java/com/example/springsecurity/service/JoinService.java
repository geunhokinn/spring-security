package com.example.springsecurity.service;

import com.example.springsecurity.dto.JoinDTO;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void joinProcess(JoinDTO joinDTO) {

        // db 에 이미 동일한 username 을 가진 멤버가 존재하는지 검증
        Boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if (Boolean.TRUE.equals(isUser)) {
            throw new RuntimeException("동일한 아이디가 이미 존재합니다.");
        }

        User user = User.buildUser(
                joinDTO.getUsername(),
                bCryptPasswordEncoder.encode(joinDTO.getPassword()), // One-way hashing processing
                "ROLE_USER"
        );

        userRepository.save(user);
    }
}
