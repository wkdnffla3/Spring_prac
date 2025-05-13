package com.example.backend_session_security.controller;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import com.example.backend_session_security.dto.LoginRequest;

@RestController
@RequestMapping("/api")
public class AuthController {

  @PostMapping("/login")
  public String login(@RequestBody LoginRequest request, HttpSession session) {
    if ("user".equals(request.getUsername()) && "pass".equals(request.getPassword())) {
      session.setAttribute("user", request.getUsername());

      // 현재 요청에서 인증 정보를 확인하기 위한 설정
      UsernamePasswordAuthenticationToken authentication = 
          new UsernamePasswordAuthenticationToken(request.getUsername(), null, List.of());
      SecurityContextHolder.getContext().setAuthentication(authentication);
      // 다음 요청에도 인증 정보를 확인하기 위해 세션에 저장
      session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

      return "로그인 성공";
    }
    return "로그인 실패";
  }

  @GetMapping("/session-user")
  public String getSessionUser(HttpSession session) {
    Object user = session.getAttribute("user");
    return user != null ? user.toString() : "";
  }

  @GetMapping("/data")
  public String data(HttpSession session) {
    Object user = session.getAttribute("user");
    return user != null ? "비밀 데이터: " + user : "로그인 안 됨";
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "로그아웃 완료";
  }
}