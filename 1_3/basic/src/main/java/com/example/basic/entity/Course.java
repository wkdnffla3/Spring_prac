package com.example.basic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Course {
    @Id
    int id;

    @Column(length = 50, nullable = false)
    String name;

    @ManyToOne // 전공은 여러개니까
    @JoinColumn(name = "major_id")
    Major major; // major_id
    // String course_id;
    
}
