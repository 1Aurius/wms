package com.example.wms.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "passKeyForTestingHasToBe32Chars1";
    private static final long EXPIRATION = 1000 * 60 * 60 * 8; // 8 hours
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    // Generate token from user
    public static String generateToken(String username, boolean isAdmin, boolean isArmazem) {
        return Jwts.builder()
                .setSubject(username)
                .claim("isAdmin", isAdmin)
                .claim("isArmazem", isArmazem)
                .claim("isManager", isArmazem)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY)
                .compact();
    }

    // Validate and get username from token
    public static String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}