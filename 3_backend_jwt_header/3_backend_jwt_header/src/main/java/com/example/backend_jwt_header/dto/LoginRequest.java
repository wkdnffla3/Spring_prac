package com.example.backend_jwt_header.dto;

import lombok.Data;

@Data
public class LoginRequest {
  String username;
  String password;
}

