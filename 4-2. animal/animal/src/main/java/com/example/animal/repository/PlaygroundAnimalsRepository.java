package com.example.animal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.animal.entity.Playground;
import com.example.animal.entity.PlaygroundAnimals;

public interface PlaygroundAnimalsRepository extends JpaRepository<PlaygroundAnimals, Long> {
  List<Playground> findByAddress(String address);
}
