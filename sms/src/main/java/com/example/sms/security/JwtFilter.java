package com.example.sms.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class JwtFilter extends OncePerRequestFilter {
    
    private final JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            
            String authorizationHeader = request.getHeader("Authorization");
            
            String userId = null;
            String jwtToken = null;
            String role = null;
            
            if (authorizationHeader != null && authorizationHeader
                    .startsWith("Bearer ")) {
                
                jwtToken = authorizationHeader.substring(7);
                
                try {
                    String tokenType = jwtUtil.extractTokenType(jwtToken);
                    if ("REFRESH".equals(tokenType)) {
                        log.warn("Refresh token attempted to access API");
                        
                        throw new InsufficientAuthenticationException(
                                "Refresh token cannot access APIs");
                    }
                    
                    userId = jwtUtil.extractUserId(jwtToken);
                    role = jwtUtil.extractUserRole(jwtToken);
                } catch (Exception e) {
                    log.error("JWT parsing failed", e);
                    throw new BadCredentialsException("Invalid JWT token", e);
                    
                }
            }
            
            if (userId != null && SecurityContextHolder
                    .getContext().getAuthentication() == null) {
                
                if (jwtUtil.isTokenValid(jwtToken, userId)) {
                    
                    List<SimpleGrantedAuthority> authorities =
                            List.of(new SimpleGrantedAuthority(
                                    "ROLE_" + role));
                    
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userId,
                                    null,
                                    authorities
                            );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );
                    
                    SecurityContextHolder.getContext()
                            .setAuthentication(authToken);
                }
            }
        } catch (JwtException e) {
            
            log.error("Invalid JWT token: {}", e.getMessage());
            
            throw new BadCredentialsException(
                    "Invalid or malformed JWT token ", e);
        }
        filterChain.doFilter(request, response);
    }
}
