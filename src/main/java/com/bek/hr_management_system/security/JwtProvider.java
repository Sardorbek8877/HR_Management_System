package com.bek.hr_management_system.security;

import com.bek.hr_management_system.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {

    private static final String secret = "secretword";
    private static final long expireTime = 10000000;
    public String generateToken(String username, Set<Role> roles){
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.ES512, secret)
                .compact();
    }
}
