package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

