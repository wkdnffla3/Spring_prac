package com.example.security.controller;

import java.beans.JavaBean;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "안녕하세요!";
    }

    // [HelloController.java 추가]
    @GetMapping("/admin")
    public String admin() {
        return "관리자 전용 페이지입니다.";
    }
}
