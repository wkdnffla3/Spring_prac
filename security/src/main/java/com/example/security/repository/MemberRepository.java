package com.example.security.repository;

import com.example.security.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MemberRepository extends JpaRepository<Member, String> {
}
