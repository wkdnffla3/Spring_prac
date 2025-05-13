package com.example.table_order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class MenuList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menu_id;
    private String menu_name;
    private String desc;
    private Integer price;

    @Lob
    @Column(name = "menu_image", columnDefinition="LONGBLOB")
    private byte[] image1;
    
}
