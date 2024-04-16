package com.db.dbpautasbackend.fixture;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public interface TokenFixture {

    static String builderDefault(String issuer, String secret){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return builder()
                .withIssuer(issuer)
                .sign(algorithm);
    }

    static String builderComIssuerInvalido(String secret){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return builder().withIssuer("Issuer Inválido")
                        .sign(algorithm);
    }

    static String builderComAlgoritimoInvalido(String issuer, String secret){
        Algorithm algorithm = Algorithm.HMAC384(secret);
        return builder().withIssuer(issuer)
                        .sign(algorithm);
    }

    static String builderComSecretInvalido(String issuer){
        Algorithm algorithm = Algorithm.HMAC256("Secret Inválido");
        return builder().withIssuer(issuer)
                        .sign(algorithm);
    }

    private static JWTCreator.Builder builder(){
        return JWT.create()
                    .withSubject("15333737525")
                    .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")));
    }
}
