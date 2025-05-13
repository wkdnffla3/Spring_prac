package com.example.backend_session.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import com.example.backend_session.dto.LoginRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

  @PostMapping("/login")
  public String login(@RequestBody LoginRequest request, HttpSession session) {
    if ("user".equals(request.getUsername()) && "pass".equals(request.getPassword())) {
      session.setAttribute("user", request.getUsername());
      return "로그인 성공";
    }
    return "로그인 실패";
  }

  @GetMapping("/data")
  public String getData(HttpSession session) {
    Object user = session.getAttribute("user");
    if (user != null) {
      return "비밀 데이터: [" + user + "]님 전용";
    } else {
      return "로그인 안 됨";
    }
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "로그아웃 완료";
  }

  @GetMapping("/session-user")
  public String getSessionUser(HttpSession session) {
    Object user = session.getAttribute("user");
    if (user != null) {
      return user.toString();
    } else {
      return "";
    }
  }
}