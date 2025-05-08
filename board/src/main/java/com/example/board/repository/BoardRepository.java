package com.example.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
  
    Optional findById(long id);

    List<Board> findByTitleContaining(String search);
    
    
    Page<Board> findByTitleContaining(String search, Pageable pageable);
}