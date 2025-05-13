package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class AdminController {
 @GetMapping("/admin-page")
 @ResponseBody
public String adminPage() {
 return "관리자 전용 페이지입니다.";
 }
}
