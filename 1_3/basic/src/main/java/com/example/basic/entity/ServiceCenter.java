package com.example.basic.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ServiceCenter {
    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    // 따로 지정을 하지 않으면 db_seq가 50씩 증가 시킨다 서버를 껏다 켯다 하면
    Integer id;
    String customer;
    String prdName;
    LocalDateTime purDate;
    Date vstDate;
}
