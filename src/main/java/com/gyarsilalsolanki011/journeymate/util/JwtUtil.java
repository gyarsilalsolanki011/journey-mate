package com.gyarsilalsolanki011.journeymate.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.expiration.time}")
    private long EXPIRATION_TIME;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    private Boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().after(new Date());
    }

    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .header().empty().add("typ", "JWT")
                .add("iss", "JourneyMate").add("target", "AppCustomer")
                .and()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSecretKey())
                .compact();
    }

    private Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(Authentication authentication){
        Map<String, Object> claims = new HashMap<>();

        if (!authentication.getAuthorities().isEmpty()) {
            claims.put("role", authentication.getAuthorities().iterator().next().getAuthority());
        }
        return  createToken(claims, authentication.getName());
    }

    public String extractEmail(String token){
        return extractClaims(token).getSubject();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        String username = extractEmail(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
