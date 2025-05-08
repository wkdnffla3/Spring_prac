package com.example.basic.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "emp")
@Data
public class Emp {
    @Id
    @Column(nullable = false)
    Integer empno;

    @Column(length = 10)
    String ename;

    @Column(length = 9)
    String job;

    Integer mgr;

    LocalDateTime hiredate;
    Integer sal;
    Integer comm;
    
    @ManyToOne
    @JoinColumn(name="deptno")
    Dept dept;

}
