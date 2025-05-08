package com.example.basic.entity;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "titanic")
public class Titanic {
    @Id Long index;
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
