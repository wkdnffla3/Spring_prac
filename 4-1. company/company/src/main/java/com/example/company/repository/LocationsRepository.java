package com.example.company.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.company.entity.Locations;

public interface LocationsRepository extends JpaRepository<Locations, Integer> {

    List<Locations> findByStateProvince(String string);
  
}
