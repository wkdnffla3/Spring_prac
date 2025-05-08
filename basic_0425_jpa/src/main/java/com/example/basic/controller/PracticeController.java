package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PracticeController {
  @GetMapping("/req/data")
  @ResponseBody
  public Map<String, Object> reqData(String area, Integer score) {
    Map<String, Object> map = new HashMap<>();
    if(area != null) map.put("area", area);
    if(score != null) map.put("score", score);
    return map;
  }
  @GetMapping("/req/data2")
  @ResponseBody
  public Map<String, Object> reqData2(
      @RequestParam Map<String, Object> map) {
    return map;
  }

  @GetMapping("/html/exam")
  public String htmlExam() {
    return "html/exam";
  }
  
  @GetMapping("/json/exam")
  @ResponseBody
  public Map<String, Object> jsonExam() {
    Map<String, Object> map = new HashMap<>();
    map.put("count", 2);

    List<Map<String, String>> list = new ArrayList<>();

    Map<String, String> m = new HashMap<>();
    m.put("name", "가");
    m.put("userId", "A");
    m.put("userPassword", "1");
    list.add(m);

    m = new HashMap<>();
    m.put("name", "나");
    m.put("userId", "B");
    m.put("userPassword", "2");
    list.add(m);
    
    map.put("list", list);

    return map;
  }  
}
