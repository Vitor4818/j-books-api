package com.JBooks.J_books.service;

import com.JBooks.J_books.model.Token;
import com.JBooks.J_books.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private final Algorithm algorithm = Algorithm.HMAC256("segredo-super-seguro");
    private final Instant expiresAt = LocalDateTime.now().plusMinutes(120).toInstant(ZoneOffset.ofHours(-3));

    public Token criarToken(Usuario usuario) {
        var jwt = JWT.create()
                .withSubject(usuario.getId().toString())
                .withClaim("email", usuario.getEmail())
                .withExpiresAt(expiresAt)
                .sign(algorithm);
        return new Token(jwt, "Bearer", usuario.getEmail());
    }

    public String getEmailFromToken(String token){

        var jwtVerified = JWT.require(algorithm).build().verify(token);
        return jwtVerified.getClaim("email").asString();
    }



}
