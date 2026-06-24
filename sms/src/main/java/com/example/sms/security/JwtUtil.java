package com.example.sms.security;

import com.example.sms.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;
    
    /** Expiration time for access tokens,
     * injected from application properties. */
    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;
    
    /** Expiration time for refresh tokens,
     * injected from application properties. */
    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;
    
    private SecretKey signingKey;

    @PostConstruct
    public void init(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }
    
   public String generateAccessToken(User user) {
        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("type", "ACCESS")
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis()
                                + accessTokenExpiration))
                .signWith(signingKey, Jwts.SIG.HS256)
                .compact();
   }
   
   public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("type", "REFRESH")
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis()
                                + refreshTokenExpiration))
                .signWith(signingKey, Jwts.SIG.HS256)
                .compact();
   }
   
   public Claims extractClaims (String token){
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
   
   public String extractUserId(String token){
        return extractClaims(token).getSubject();
   }
   
   public boolean isTokenExpired(String token){
        return extractClaims(token)
                .getExpiration()
                .before(new Date());
   }
   
   public boolean isTokenValid(String token, String userId){
        final String extractedPhone = extractUserId(token);
        return extractedPhone.equals(userId) && !isTokenExpired(token);
   }
   
   public String extractTokenType(String token){
        return extractClaims(token).get("type", String.class);
   }
   
   public String extractUserRole(String token){
        return extractClaims(token).get("role", String.class);
   }
}
