package com.db.dbpautasbackend.service;


import com.db.dbpautasbackend.fixture.TokenFixture;
import com.db.dbpautasbackend.service.impl.TokenServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class TokenServiceImplIntegrationTest {
    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private Environment environment;

    private String issuer;
    private String secret;

    @BeforeEach
    void setUp(){
        issuer = environment.getProperty("jwt.issuer");
        secret = environment.getProperty("jwt.secret");
    }

    @Test
    @DisplayName("Dado um token válido, quando verificado, deve retornar que é válido.")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void isTokenValidoTest(){
        String token = TokenFixture.builderDefault(issuer, secret);
        boolean resultado = tokenService.isTokenValido(token);
        assertEquals(true, resultado);
    }

    @Test
    @DisplayName("Dado um token com issuer inválido, quando verificado, deve retornar que é inválido.")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void isTokenComIssuerInvalidoTest(){
        String token = TokenFixture.builderComIssuerInvalido(secret);
        boolean resultado = tokenService.isTokenValido(token);
        assertEquals(false, resultado);
    }

    @Test
    @DisplayName("Dado um token com algoritmo inválido, quando verificado, deve retornar que é inválido.")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void isTokenComAlgoritmoInvalidoTest(){
        String token = TokenFixture.builderComAlgoritimoInvalido(issuer, secret);
        boolean resultado = tokenService.isTokenValido(token);
        assertEquals(false, resultado);
    }

    @Test
    @DisplayName("Dado um token com secret inválido, quando verificado, deve retornar que é inválido.")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void isTokenComSecretInvalidoTest(){
        String token = TokenFixture.builderComSecretInvalido(issuer);
        boolean resultado = tokenService.isTokenValido(token);
        assertEquals(false, resultado);
    }
}
