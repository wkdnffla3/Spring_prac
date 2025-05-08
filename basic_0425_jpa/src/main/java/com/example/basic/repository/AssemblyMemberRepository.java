package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.AssemblyMember;

public interface AssemblyMemberRepository 
    extends JpaRepository<AssemblyMember, Integer> {
  
}
