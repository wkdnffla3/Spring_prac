package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.Board;
import com.example.board.entity.Like;
import com.example.board.entity.User;

import java.util.List;


public interface LikeRepository extends JpaRepository<Like, Long>{
    List<Like> findByBoard(Board board);
    //이 사용자가 이 게시글에 좋아요를 했는가 안했는가.countByAllIgnoreCase
    // 게시물 번호별 좋아요 목록
    //게시물 별 사용자별 좋아요 여부 확인
    List findByBoardAndUser(Board board,User user);
}
