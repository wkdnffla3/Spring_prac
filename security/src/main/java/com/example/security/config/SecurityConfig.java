package com.example.security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.cors.CorsConfiguration;

import com.example.security.filter.JwtAuthFilter;
import com.example.security.util.JwtUtil;

@Configuration
public class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http, JwtUtil jwtUtil,
                        UserDetailsService userDetailsService) throws Exception {
                http
                                // .csrf(csrf->{
                                // csrf.disable();//csrf 공격이 와도 대응 안하겠다.
                                // })
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/admin").authenticated()
                                                .requestMatchers("/my-page").authenticated()
                                                .requestMatchers("/admin-page").hasRole("ADMIN")
                                                .requestMatchers("/user-page").hasRole("USER")
                                                .requestMatchers("/jwt/login").permitAll()
                                                .requestMatchers("/jwt/protected").authenticated()

                                                .anyRequest().permitAll())
                                .addFilterBefore(new JwtAuthFilter(jwtUtil, userDetailsService),
                                                UsernamePasswordAuthenticationFilter.class)
                                .csrf(csrf -> csrf.disable())

                                .formLogin(form -> form
                                                .loginPage("/login") // 아직 없지만 나중에 만들 예정
                                                .defaultSuccessUrl("/hello", true)
                                                .permitAll())
                                .rememberMe(r -> r
                                                .key("remember-me-key") // 서버 비밀키
                                                .rememberMeParameter("remember-me") // form 체크박스 name
                                                .tokenValiditySeconds(60 * 60 * 24 * 7) // 7일
                                )

                                .logout(logout -> logout
                                                // .logoutSuccessUrl("/hello"));
                                                .logoutUrl("/logout") // default: /logout (POST)
                                                .logoutSuccessUrl("/login?logout=true")
                                                .invalidateHttpSession(true) // 세션 제거
                                                .deleteCookies("JSESSIONID")); // 쿠키 제거

                http.cors(cors -> cors
                                .configurationSource(request -> {
                                        CorsConfiguration config = new CorsConfiguration();
                                        config.setAllowedOrigins(List.of("http://localhost:5500"));

                                        config.setAllowedMethods(List.of("GET", "POST"));
                                        config.setAllowCredentials(true);
                                        config.setAllowedHeaders(List.of("*"));
                                        return config;
                                }));

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
                return http.getSharedObject(AuthenticationManagerBuilder.class).build();
        }

}
