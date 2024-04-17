package com.db.dbpautasbackend.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.db.dbpautasbackend.repository.UsuarioRepository;
import com.db.dbpautasbackend.service.TokenService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl implements TokenService {

    private Environment environment;
    private UsuarioRepository usuarioRepository;
    private String jwtSecret;
    private String issuer;
    private Algorithm algorithm;

    public TokenServiceImpl(Environment environment, UsuarioRepository usuarioRepository) {
        this.environment = environment;
        this.usuarioRepository = usuarioRepository;
        this.jwtSecret = environment.getProperty("jwt.secret");
        this.issuer = environment.getProperty("jwt.issuer");
        this.algorithm = Algorithm.HMAC256(jwtSecret);
    }

    public String gerarToken(String cpf){
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(cpf)
                .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);
    }

    public boolean isTokenValido(String token){
        try {
            JWT.require(algorithm)
                    .withIssuer(issuer)
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
        return JWT.require(Algorithm.HMAC256(jwtSecret))
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getSubject();
    }

    @Override
    public String extrairToken(String authorizationHeader) {
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        } else {
            return "";
        }
    }

}
