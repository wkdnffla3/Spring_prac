package com.example.basic.entity;

import java.util.ArrayList;
import java.util.List;

// import org.attoparser.dom.Comment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity @Data
public class Post {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 Long id;
 String title;

 @OneToMany(
    mappedBy = "post", 
    cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, 
    orphanRemoval = true)
 List<Comment> comments = new ArrayList<>();

 public void addComment(Comment comment) {
   comments.add(comment);
   comment.setPost(this);
 }

 public void removeComment(Comment comment) {
   comments.remove(comment);
   comment.setPost(null); // 관계 끊기
 }
}

