package com.example.backend_jwt_cookie.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.example.backend_jwt_cookie.jwt.JwtUtil;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

  @Autowired
  JwtUtil jwtUtil;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .cors(cors -> cors.configurationSource(request -> {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        return config;
      }))
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/api/login", "/api/logout").permitAll()
          .anyRequest().authenticated())
      .addFilterBefore((req, res, chain) -> {
        Cookie[] cookies = ((HttpServletRequest)req).getCookies();
        if (cookies != null) {
          Arrays.stream(cookies)
              .filter(c -> c.getName().equals("jwt"))
              .findFirst()
              .ifPresent(cookie -> {
                try {
                  String user = jwtUtil.validate(cookie.getValue());
                  UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, List.of());
                  SecurityContextHolder.getContext().setAuthentication(auth);
                } catch (Exception ignored) {
                }
              });
        }
        chain.doFilter(req, res);
      }, BasicAuthenticationFilter.class);
    return http.build();
  }
}
