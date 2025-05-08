package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}

