package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.model.dto.LoginRequest;
import com.db.dbpautasbackend.model.dto.LoginResponse;
import com.db.dbpautasbackend.model.enums.Papel;
import com.db.dbpautasbackend.fixture.LoginRequestFixture;
import com.db.dbpautasbackend.service.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class LoginControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TokenService tokenService;

    @Test
    @DisplayName("Dado um login válido salvo no banco de dados, quando logado, deve gerar um token corretamente")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void loginTest(){
        LoginRequest login = LoginRequestFixture.buiderDefault();
        HttpEntity<LoginRequest> requisicao = new HttpEntity<>(login);

        ResponseEntity<LoginResponse> resposta = restTemplate.postForEntity("http://localhost:" + port + "/login", requisicao, LoginResponse.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertTrue(tokenService.isTokenValido(resposta.getBody().token()));
        assertEquals(Papel.ADMIN, resposta.getBody().papel());
    }

    @ParameterizedTest
    @MethodSource("logins")
    @DisplayName("Dado um login inválido salvo no banco de dados, quando logado, deve retornar status 400")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void loginTestWithInvalidLogin(LoginRequest login){
        HttpEntity<LoginRequest> requisicao = new HttpEntity<>(login);

        ResponseEntity<String> resposta = restTemplate.postForEntity("http://localhost:" + port + "/login", requisicao, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
    }

    private static Stream<Arguments> logins() {
        return Stream.of(
                Arguments.of(LoginRequestFixture.builderComCpfBranco()),
                Arguments.of(LoginRequestFixture.builderComSenhaBranca())
        );
    }
}
