package com.example.basic.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Major {
  @Id
  @GeneratedValue(
    strategy = GenerationType.IDENTITY)
  int id;

  @Column(length = 255)
  String name;

  Integer maxPrsn;

  @Temporal(TemporalType.DATE)
  Date ebtbDate;
}
