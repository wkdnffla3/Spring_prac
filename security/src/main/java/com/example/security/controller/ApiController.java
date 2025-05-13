package com.example.security.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    // @CrossOrigin(origins = "http://localhost:3000")// 기본 상태
    @CrossOrigin// 이렇게 하면 잘된다.
    // security에 써야지가 아니라 controller 에 써야 한다.
    @GetMapping("/message")
    public String message() {
        return "서버에서 보낸 메시지입니다.";
    }
}
