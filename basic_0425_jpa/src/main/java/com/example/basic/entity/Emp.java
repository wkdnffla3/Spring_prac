package com.example.basic.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity @Data
public class Emp {
  @Id
  int empno;

  @Column(length = 10)
  String ename;
  @Column(length = 9)
  String job;
  Integer mgr;
  LocalDateTime hiredate;
  Integer sal;
  Integer comm;
  
  @ManyToOne
  @JoinColumn(name = "deptno")
  Dept dept; // dept_deptno 기본값

}
