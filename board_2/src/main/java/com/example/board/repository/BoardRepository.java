package com.example.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
  //              반드시 Containing => Like % 검색 가능
  Page<Board> findByTitleContaining(
    String title, Pageable pageable);
}