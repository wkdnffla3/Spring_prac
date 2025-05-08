package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.entity.Major;
import com.example.basic.entity.Titanic;
import java.util.List;


@Repository
public  interface TitanicRepository extends JpaRepository<Titanic,Integer>{

    // 탑승한 항구가 Queenstown인 모든 승객 조회
    // find by "column name"
    List<Titanic> findByEmbarked(String embarked);
    // 혼자 승선한 승객 수 조회
    // find by ""
    List<Titanic> findBySibSpAndParch(int sibSp, int parch);
    // 이름에 smith가 포함된 승객 조회
    List<Titanic> findByNameStartingWith(String name);
    
    // 3등석 승객의 생존자 명단을 나이가 많은 순서대로 조회

    List<Titanic> findByPclassAndSurvivedOrderByAgeDesc(Long pclass, Long survived);
}  