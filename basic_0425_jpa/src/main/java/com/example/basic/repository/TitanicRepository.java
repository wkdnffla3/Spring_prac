package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.entity.Titanic;

@Repository
public interface TitanicRepository 
    extends JpaRepository<Titanic, Long> {
  
}
