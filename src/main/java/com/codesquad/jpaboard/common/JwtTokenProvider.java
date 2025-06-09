package com.codesquad.jpaboard.common;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;


@Component
public class JwtTokenProvider {
  @Value("${jwt.secret}")
  private String base64Secret;

  private final long validityInMs = 6 * 3600000; // 6시간
  private Key key;

  // 생성자 또는 @PostConstruct로 Key 초기화
  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(java.util.Base64.getUrlDecoder().decode(base64Secret));
  }
  //  private Key key = Keys.hmacShaKeyFor(java.util.Base64.getUrlDecoder().decode(base64Secret));

  public String createToken(Long userId, String name) {
    Claims claims = Jwts.claims().setSubject(name);
    claims.put("userId", userId);

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMs);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, key)
        .compact();
  }

  public Claims parseClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build()
        .parseClaimsJws(token).getBody();
  }
}
