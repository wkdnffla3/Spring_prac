package com.example.backend_session_security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http

              .csrf(csrf -> csrf.disable())
              .cors(cors -> cors.configurationSource(request -> { // controller 에 하지 않고 config 에 했다.
                  CorsConfiguration config = new CorsConfiguration();
                  config.setAllowedOrigins(List.of("http://localhost:5173"));
                  config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                  config.setAllowedHeaders(List.of("*"));
                  config.setAllowCredentials(true);
                  return config;
              }))
              .authorizeHttpRequests(auth -> auth
                      // 인증 없이 기능하게 하는 URL
                      .requestMatchers("/api/login", "/api/logout", "/api/session-user").permitAll()
                      .anyRequest().authenticated())
              // 기본 로그인 비활성화 (로그인이 되어 있지 않으면 /login으로 연결)
              .formLogin(login -> login.disable())
              // 같은 계정 동시 접속자 1명으로 제한
              .sessionManagement(management -> management
                      .maximumSessions(1)
                      .maxSessionsPreventsLogin(true));
        // .sessionManagement(management ->
        // management.maximumSessions(1));

    return http.build();
  }
}