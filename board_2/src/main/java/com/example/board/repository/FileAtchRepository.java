package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.FileAtch;

public interface FileAtchRepository 
    extends JpaRepository<FileAtch, Long> {
  
}
