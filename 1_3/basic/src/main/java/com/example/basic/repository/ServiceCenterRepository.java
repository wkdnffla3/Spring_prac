package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.entity.ServiceCenter;

@Repository
public interface ServiceCenterRepository
extends JpaRepository<ServiceCenter, Integer> {
}
