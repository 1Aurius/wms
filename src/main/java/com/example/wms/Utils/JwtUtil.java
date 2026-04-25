package com.example.wms.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "your-secret-key-here"; // keep this private
    private static final long EXPIRATION = 1000 * 60 * 60 * 8; // 8 hours

    // Generate token from user
    public static String generateToken(String username, boolean isAdmin, boolean isManager) {
        return Jwts.builder()
                .setSubject(username)
                .claim("isAdmin", isAdmin)
                .claim("isManager", isManager)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    // Validate and get username from token
    public static String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}