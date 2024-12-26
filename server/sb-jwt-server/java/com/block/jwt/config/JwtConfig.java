package com.block.jwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {
    Key key;
    // 24시간 유효 토큰 만료 시간 설정 24 * 60 * 60 * 1000
    long tokenValidMilisecond = 24 * 60 * 60 * 1000;

    // application.yml 에서 jwt.secret 값을 가져온다.
    public JwtConfig(@Value("${jwt.secret}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(Long userId) {
        Claims claims = (Claims) Jwts.claims();
        claims.put("userId", userId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidMilisecond);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getTokenClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)  // Key를 SecretKey로 캐스팅
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
