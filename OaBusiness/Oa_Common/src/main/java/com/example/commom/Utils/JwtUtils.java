package com.example.commom.Utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtils {

    private static final byte[] secretBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
    private static final Key key = Keys.hmacShaKeyFor(secretBytes);
    private static final long expirationTime = 3600000; // 1 hour in milliseconds

    public static String generateToken(String subject) {
        String jwt = Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
        return jwt;
    }

    public static Claims decodeToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (JwtException e) {
            throw new RuntimeException("Invalid token.");
        }
    }
}
