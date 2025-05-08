package com.example.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.Dept;
import com.example.basic.entity.Emp;

public interface EmpRepository 
    extends JpaRepository<Emp, Integer> {
  // Query Method
  List<Emp> findByEname(String ename);
  List<Emp> findByDept(Dept dept);
  List<Emp> findByEnameContainingAndJobContaining(
    String ename, String job);
}
