package com.example.backend_jwt_cookie.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
  private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private final long expire = 1000 * 60 * 60;

  public String generate(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + expire))
        .signWith(key)
        .compact();
  }

  public String validate(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build()
        .parseClaimsJws(token)
        .getBody().getSubject();
  }
}
