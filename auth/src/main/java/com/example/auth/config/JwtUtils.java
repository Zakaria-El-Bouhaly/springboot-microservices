package com.example.auth.config;

import com.example.auth.models.Role;
import com.example.auth.models.UserEntity;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(UserEntity user) {
        String username = user.getEmail();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + expiration);

        String token = Jwts.builder().setSubject(username).setIssuedAt(currentDate).setExpiration(expiryDate)
                .addClaims(authoritiesToClaims(user))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
                .compact();
        return token;

    }

    public Map<String, Object> authoritiesToClaims(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles= user.getRoles().stream().map(r->r.getName()).toList();
        claims.put("roles", roles);
        return claims;
    }

 


}
