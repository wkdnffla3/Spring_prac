package com.example.basic.entity;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Entity(name = "major")
@Data
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(length = 300, nullable = true)
    String name;

    @Column(nullable = false)
    int maxPrsn;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    Date ebtbDate;

    
    
}
