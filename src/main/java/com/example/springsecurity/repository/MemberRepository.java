package com.example.springsecurity.repository;

import com.example.springsecurity.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsByUsername(String username);
}
