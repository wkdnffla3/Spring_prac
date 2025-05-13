package com.example.backend_jwt_header.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend_jwt_header.dto.LoginRequest;
import com.example.backend_jwt_header.jwt.JwtUtil;

@RestController
@RequestMapping("/api")
public class AuthController {

  @Autowired
  JwtUtil jwtUtil;

  @PostMapping("/login")
  public String login(@RequestBody LoginRequest request) {
    if ("user".equals(request.getUsername()) && "pass".equals(request.getPassword())) {
      return jwtUtil.generate(request.getUsername());
    }
    return "fail";
  }

  @GetMapping("/data")
  public String getData() {
    return "인증된 사용자만 볼 수 있는 데이터입니다";
  }
}
