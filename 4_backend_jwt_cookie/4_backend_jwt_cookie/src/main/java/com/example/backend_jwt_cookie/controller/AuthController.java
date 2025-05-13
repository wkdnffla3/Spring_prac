package com.example.backend_jwt_cookie.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend_jwt_cookie.jwt.JwtUtil;
import com.example.backend_jwt_cookie.dto.LoginRequest;

@RestController
@RequestMapping("/api")
public class AuthController {

  @Autowired
  JwtUtil jwtUtil;

  @PostMapping("/login")
  public String login(@RequestBody LoginRequest request, HttpServletResponse response) {
    if ("user".equals(request.getUsername()) && "pass".equals(request.getPassword())) {
      String token = jwtUtil.generate(request.getUsername());
      Cookie cookie = new Cookie("jwt", token);
      cookie.setHttpOnly(true);
      cookie.setPath("/");
      cookie.setMaxAge(60 * 60); // 1시간
      response.addCookie(cookie);
      return "로그인 성공";
    }
    return "로그인 실패";
  }

  @GetMapping("/data")
  public String data() {
    return "쿠키로 인증된 사용자만 볼 수 있는 데이터";
  }

  @PostMapping("/logout")
  public void logout(HttpServletResponse response) {
    Cookie cookie = new Cookie("jwt", null);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    cookie.setMaxAge(0); // 즉시 만료
    response.addCookie(cookie);
  }
}
