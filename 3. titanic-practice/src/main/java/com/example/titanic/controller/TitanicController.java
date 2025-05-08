package com.example.titanic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.val;

@RestController
public class TitanicController {


  @Autowired JdbcTemplate jdbcTemplate;

  @GetMapping("survived")
  public List<Map<String, Object>> survived(@RequestParam Integer value) {
    String sql = "select * from titanic where survived = " + value;
    return jdbcTemplate.queryForList(sql,value);
  }

  @GetMapping("name")
  public List name() {
    return null;
  }

}
