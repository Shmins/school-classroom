package com.school.main.methods.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.school.main.entity.utils.Token;
import com.school.main.services.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String tokenCreate(Token user) {
        return JWT.create()
                .withSubject(user.cpf())
                .withClaim("name", user.name())
                .withIssuer(user.role().toString())
                .withExpiresAt(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("sha122"));
    }

    @Override
    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("sha122"))
                .build().verify(token).getSubject();
    }

    @Override
    public String getIssuer(String token) {
        return JWT.require(Algorithm.HMAC256("sha122"))
                .build().verify(token).getIssuer();
    }
}
