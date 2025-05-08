package com.example.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.model.Computer;
import com.example.basic.model.Member;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RequestController {
  @Tag(name = "User API", description = "사용자 관련 API")
  @GetMapping("req/http")
  //               요청 데이터 처리용 도구
  public String http(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies) {
      String id = cookie.getValue();
      System.out.println(id);
    }

    String name = request.getParameter("name");
    String pageNum = request.getParameter("pageNum");
    return name + ", " + pageNum;
  }

  @GetMapping("req/path/{path1}/{path2}")
  public String path(
      @PathVariable String path1,
      @PathVariable String path2) {
    return path1 + ", " + path2;
  }

  @Tag(name = "User API", description = "사용자 관련 API")
  @GetMapping("req/model")
  public String model(
    // 생략하면 @ModelAttribute
    @ModelAttribute Computer computer, int ssd) {
    return computer.toString();
  }
}