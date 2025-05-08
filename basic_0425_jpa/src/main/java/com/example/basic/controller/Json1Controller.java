package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.model.Member;

@Controller
public class Json1Controller {
  @GetMapping("json1/string")
  @ResponseBody
  public String json() {
    return "json/string";
  }

  @GetMapping("json1/map")
  @ResponseBody
  public Map<String, Object> jsonMap(Map<String, Object> map) {
    Map<String, Object> map2 = new HashMap<String, Object>();
    map2.put("key1", "value");
    map2.put("key2", 2324);
    map2.put("key3", true);
    return map2;
  }
  @GetMapping("json1/object")
  @ResponseBody
  public Member jsonObject() { // DTO 사용
    Member member = new Member();
    member.setName("kim");
    return member;
  }
  @GetMapping("json1/list")
  @ResponseBody
  public List<Member> jsonList() {
    List<Member> list = new ArrayList<>();

    list.add(new Member("kim"));
    list.add(new Member("kim1"));
    list.add(new Member("kim2"));
    list.add(new Member("kim3"));

    return list;
  }

  @GetMapping("json1/map2")
  @ResponseBody
  public ResponseEntity<String> jsonMap2(Map<String, Object> map) {
    return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body("123");
  }
}
