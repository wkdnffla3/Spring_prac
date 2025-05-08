package com.example.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.entity.Dept;
import com.example.basic.entity.Emp;

@Repository // 생략 가능 bean 등록 되면
public interface EmpRopository extends JpaRepository<Emp,Integer>{

    //Query Method 라고 한다.
    List<Emp> findByEname(String ename);
    List<Emp> findByDept(Dept dept);
    List<Emp> findByEnameContainingAndJobContaining(String ename, String job);
    
} 