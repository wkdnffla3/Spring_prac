package com.example.basic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Titanic {
  @Id
  Long index;
  @Column(name = "passenger_id")
  Long passengerId;
  Long survived;
  Long pclass;
  String name;
  String sex;
  Double age;
  Long sibSp;
  Long parch;
  String ticket;
  Double fare;
  String cabin;
  String embarked;
}