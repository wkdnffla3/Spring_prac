package com.example.backend_jwt_header.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.example.backend_jwt_header.jwt.JwtUtil;

import java.io.IOException;
import java.util.List;

@Configuration
public class SecurityConfig {

  private final JwtUtil jwtUtil;

  public SecurityConfig(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

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
          .requestMatchers("/api/login").permitAll()
          .anyRequest().authenticated())
      .addFilterBefore((req, res, chain) -> {
        String auth = ((HttpServletRequest)req).getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ")) {
          String token = auth.substring(7);
          try {
            String user = jwtUtil.validate(token);
             UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, List.of());
            SecurityContextHolder.getContext().setAuthentication(authToken);
          } catch (Exception ignored) {
          }
        }
        chain.doFilter(req, res);
      }, BasicAuthenticationFilter.class);
    return http.build();
  }
}
