package com.example.sms.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class JwtUtil {

    @Value("${secret.key}")
    private String secretKey;

    public SecretKey getSigningKey (){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    public Claims extarctAllClaims (String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e){
            throw e;
        }
    }
    public String extractUserName (String token){
        return extarctAllClaims(token).getSubject();
    }
    public Date extractExpiration (String token){
        return extarctAllClaims(token).getExpiration();
    }
    public Boolean isTokenExpired (String token){
        return extractExpiration(token).before(new Date());
    }
    public String createToken(Map<String,Object>claims,String subject){
        Date issueAt = new Date(System.currentTimeMillis());
        // Expiration 5 hours
        Date expiration = new Date(System.currentTimeMillis() + 1000*60*60*5);
        return Jwts.builder()
                .setHeaderParam("TYP","JWT")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(issueAt)
                .setExpiration(expiration) //Expiration 5 hours
                .signWith(getSigningKey())
                .compact();
    }
    public String genrateToken (String userName){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userName);
    }
    public Boolean validateToken (String token){
        try{
            return !isTokenExpired(token);
        }catch (Exception e){
            return false;
        }
    }
}
