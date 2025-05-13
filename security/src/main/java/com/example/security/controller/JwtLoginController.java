package com.example.security.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.security.util.JwtUtil;

import java.security.Principal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/jwt")
public class JwtLoginController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            body.get("username"), body.get("password")));
            String token = jwtUtil.createToken(authentication.getName());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }

        // String username = body.get("username");
        // String password = body.get("password");
        // if ("user1".equals(username) && "1234".equals(password)) {
        // String token = jwtUtil.createToken(username);
        // return ResponseEntity.ok().body(Map.of("token", token));
        // }
        // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
    }

    @GetMapping("/protected")
    public String protectedArea(
            Principal principal, Authentication authentication,
            @AuthenticationPrincipal UserDetails userDetails) {
        String s1 = principal.getName();
        String s2 = authentication.getName();
        String s3 = userDetails.getUsername();
        return "JWT 인증된 사용자만 접근 가능한 영역입니다.\n" + s1 + s2 + s3;
    }
}
