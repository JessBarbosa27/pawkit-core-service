package com.jessbarbosa.apps.pawkit_core_service.provider;

import com.jessbarbosa.apps.pawkit_core_service.model.Role;
import com.jessbarbosa.apps.pawkit_core_service.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private static final String secretKey = "secret"; // Ideally stored in an environment variable

    public String createToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toList()))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
