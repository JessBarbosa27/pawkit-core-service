package com.jessbarbosa.apps.pawkit_core_service.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "your-secret-key"; // Replace with your secret key

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = extractJwtFromRequest(request);

        if (jwt != null && isValidToken(jwt)) {
            Claims claims = extractClaims(jwt);
            String username = claims.getSubject();
            Authentication authentication = getAuthentication(username);

            // Set the authentication in the context
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean isValidToken(String token) {
        try {
            Claims claims = extractClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Authentication getAuthentication(String username) {
        // Fetch user roles from your database or service (e.g., from a UserService)
        return new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Optional, if needed for initialization
//    }

    @Override
    public void destroy() {
        // Optional, if needed for cleanup
    }
}
