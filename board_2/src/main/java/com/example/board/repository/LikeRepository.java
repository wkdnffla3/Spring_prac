package com.example.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.Board;
import com.example.board.entity.Like;
import com.example.board.entity.User;

public interface LikeRepository 
    extends JpaRepository<Like, Long> {
      
  // 게시물 번호별 좋아요 목록
  List<Like> findByBoard(Board board);

  // 게시물별 사용자별 좋아요 여부 확인
  Like findByBoardAndUser(Board board, User user);
}
