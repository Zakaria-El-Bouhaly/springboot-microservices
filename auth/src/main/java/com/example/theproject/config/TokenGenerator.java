package com.example.theproject.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TokenGenerator {
    public String generateToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + JwtConstants.JWT_EXPIRATION);
            String token = Jwts.builder().setSubject(username).setIssuedAt(currentDate)
                .setExpiration(expiryDate).addClaims(authoritiesToClaims(userDetails.getAuthorities()))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, JwtConstants.JWT_SECRET).compact();
        return token;

    }

    public Map<String, Object> authoritiesToClaims(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().collect(Collectors.toMap(GrantedAuthority::getAuthority, grantedAuthority -> true));
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(JwtConstants.JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JwtConstants.JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
