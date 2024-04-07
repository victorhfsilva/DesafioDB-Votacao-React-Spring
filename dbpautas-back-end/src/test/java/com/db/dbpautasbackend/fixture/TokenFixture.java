package com.db.dbpautasbackend.fixture;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public interface TokenFixture {

    String ISSUER = "DB";
    String SECRET = "nfY;z=8eZ=P;;Aos62itcz7MNHb9#x%0Bhyq%jq[4CP;29W04k";
    Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    static String builderDefault(){
        return builder().sign(ALGORITHM);
    }

    static String builderComIssuerInvalido(){
        return builder().withIssuer("Issuer Inválido")
                        .sign(ALGORITHM);
    }

    static String builderComAlgoritimoInvalido(){
        Algorithm algorithm = Algorithm.HMAC384(SECRET);
        return builder().withIssuer(ISSUER)
                        .sign(algorithm);
    }

    static String builderComSecretInvalido(){
        Algorithm algorithm = Algorithm.HMAC256("Secret Inválido");
        return builder().withIssuer(ISSUER)
                        .sign(algorithm);
    }

    private static JWTCreator.Builder builder(){
        return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject("15333737525")
                    .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")));
    }
}
