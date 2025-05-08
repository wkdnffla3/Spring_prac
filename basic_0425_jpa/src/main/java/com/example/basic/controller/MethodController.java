package com.example.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class MethodController {
  @GetMapping("req/get")
  public String get(
    HttpSession session,
    @RequestParam(defaultValue = "1") Integer page, 
    @RequestParam(defaultValue = "") String search) {
      session.setAttribute("abcd", "1234");
    return String.format(
      "%s페이지 검색어: %s", page, search);
  }

  @PostMapping("req/post")
  public String post() {
    return "POST";
  }
}
