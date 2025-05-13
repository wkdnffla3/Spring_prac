package com.example.backend_session_security.dto;

import lombok.Data;

@Data
public class LoginRequest {
  String username;
  String password;
}
