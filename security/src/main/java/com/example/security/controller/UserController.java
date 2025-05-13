package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @GetMapping("/user-page")
    @ResponseBody
    public String userPage() {
        return "일반 사용자 페이지입니다.";
    }
}
