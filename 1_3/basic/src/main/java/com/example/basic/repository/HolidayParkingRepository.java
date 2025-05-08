package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.HolidayParking;

public interface HolidayParkingRepository extends JpaRepository<HolidayParking,Integer>  {
    
}
