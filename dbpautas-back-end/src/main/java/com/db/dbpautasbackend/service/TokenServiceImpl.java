package com.db.dbpautasbackend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.db.dbpautasbackend.repository.UsuarioRepository;
import com.db.dbpautasbackend.service.interfaces.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private static final String JWT_SECRET = "nfY;z=8eZ=P;;Aos62itcz7MNHb9#x%0Bhyq%jq[4CP;29W04k";
    private static final String ISSUER = "DB";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(JWT_SECRET);

    private UsuarioRepository usuarioRepository;

    public String gerarToken(String cpf){

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(cpf)
                .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")))
                .sign(ALGORITHM);
    }

    public boolean isTokenValido(String token){
        try {
            JWT.require(ALGORITHM)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token);
            String cpf = JWT.decode(token).getSubject();
            return usuarioRepository.findUserDetailsByCpf(cpf).isPresent();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public String extrairSujeito(String token) {
        return JWT.require(Algorithm.HMAC256(JWT_SECRET))
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }

}
