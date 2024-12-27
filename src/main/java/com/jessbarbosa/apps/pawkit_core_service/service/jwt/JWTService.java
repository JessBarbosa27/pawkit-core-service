package com.jessbarbosa.apps.pawkit_core_service.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private final String SECRET_KEY = "your-secret-key";  // Secret key for signing JWTs

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))  // 1 hour expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
