package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.entity.AssemblyMember;
// import com.example.basic.entity.Major;

@Repository
public  interface AssemblyMemberRepository extends JpaRepository<AssemblyMember,Integer>{

    
}  