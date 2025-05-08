package com.example.basic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "table_exam_1")
public class TableExam1 {
  @Id
  Integer id;
  @Column(length = 100, nullable = false)
  String title;
  @Column(name = "description", length = 1000, nullable = true)
  String content;
  Long price;
  String brand;
}
