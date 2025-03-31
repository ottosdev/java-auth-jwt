package com.br.otto.financeiroestudoback.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.otto.financeiroestudoback.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secreet;
    public String generateToken(Cliente cliente) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secreet);
            String token = JWT.create().withIssuer("login-auth-api").withSubject(cliente.getEmail())
                    .withExpiresAt(getTokenExpires())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generating token: " + e.getMessage());
        }

    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secreet);
            return JWT.require(algorithm).withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e) {
            return null;
        }
    }


    private Instant getTokenExpires() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of(String.valueOf(-3)));
    }
}
