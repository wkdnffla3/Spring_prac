package com.example.basic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity @Data
public class Comment {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 Long id;
 String content;

 public Comment() {}
 public Comment(String content) {
   this.content = content;
 }

 @ManyToOne
 @JoinColumn(name = "post_id")
 Post post;
}
