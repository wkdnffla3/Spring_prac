package com.example.backend_jwt_cookie.dto;

import lombok.Data;

@Data
public class LoginRequest {
  String username;
  String password;
}

