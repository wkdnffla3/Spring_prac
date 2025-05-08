package com.example.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

  @GetMapping("/register")
  public String showForm() {
    return "register";
  }

  @PostMapping("/register")
  @ResponseBody
  public String handleRegister(
    @RequestParam String name,
    @RequestParam String age,
    @RequestParam String email
  ) {
    // 문제: name, age, email 값을 받아 출력
    return String.format("이름: %s, 나이: %s, 이메일: %s", name, age, email);
  }

  @GetMapping("/register-ajax")
  public String showForm2() {
    return "register_ajax";
  }
   @PostMapping("/register-ajax2")
  //@GetMapping("/register-ajax2")
  @ResponseBody
  public String registerAjax(
    @RequestBody Map<String, Object> body
    // @RequestParam String name,
    // @RequestParam Integer age,
    // @RequestParam String email
    ) {
    // 문제: JSON으로 전달받은 데이터를 그대로 응답
    Map<String, Object> response = new HashMap<>();

    return body.toString();
  }
}
