package com.example.security.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // templates/login.html
    }

    @GetMapping("/my-page")
    public String myPage(
        //시큐리티가 알아서 로그인??
        //httpsession 을 써서 저장했는데 이제는 시큐리티가 저장한다
        // 세션에 저장되오ㅓ 있는 로그인 정보 확인.
        Principal principal, 
        Authentication authentication,
        @AuthenticationPrincipal UserDetails userDetails
    ) 
    {
        
        System.out.println("principal: " + principal.getName());
        System.out.println("authentication: " + authentication.getName());
        System.out.println("@AuthenticationPrincipal: " + userDetails.getUsername());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("SecurityContextHolder: " + auth.getName());
        return "my-page";

    }
}