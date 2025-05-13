package com.example.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmailAndPwd(String email, String pwd);
}