package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SubmitController {
    @GetMapping("/csrf-test")
    public String testPage() {
        return "csrf-test"; // resources/templates/csrf-test.html
    }

    @PostMapping("/submit")
    @ResponseBody
    public String submit() {
        return "POST 요청 성공!";
    }
}
