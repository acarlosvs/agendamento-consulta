package com.conexa.desafio.conexadesafioantoniocarlos.domain.services.impl;

import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private static final long EXPIRATION_TIME = 1800000;

    public String generateToken(){
        return String.valueOf(Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject("Teste token")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact());
    }

    private Key getSigningKey() {
        String key = "lEAnEZSLi8qbVhnHe3Cixkq7Ty2wMs6VVu8pjPZsNfrymC5pwvxTFMQmaGIKXCwGXsV47ImKLB9W42bqoy7R8L49fAH233yOkiIhQVSqmPceHduTzrzy0tv6R2YKUlm4tVb7ERnOwnT2qdJOt8cjpvd7n2F5bSXsDXp5CBJZOsP3uoHLlHUA2YvdO17NHU58Fi38JCVmVyddMKOVfwk7iD8Ws5xZOsXhBQd8hYi0ml3XTHGY3QTckQWCLkqnAdtZ";
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims decodeToken(String authorization){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()).build()
                .parseClaimsJws(authorization)
                .getBody();
    }

    public String getPasswordEncoder(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }
}
