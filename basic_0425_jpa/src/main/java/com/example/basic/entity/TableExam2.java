package com.example.basic.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "table_exam_2")
public class TableExam2 {
  @Id
  Integer id;
  
  String name;

  String order;
  int select;

  // 세계 표준시 UTC
  @Temporal(TemporalType.DATE)
  Date birthDay;
  @Temporal(TemporalType.TIME)
  Date birthTime;
  @Temporal(TemporalType.TIMESTAMP)
  Date signupDate;

  // 현지 시각 (한국 Asia/Seoul)
  LocalDate writeDate;
  LocalTime writeTime;
  LocalDateTime writeDateTime;
}
