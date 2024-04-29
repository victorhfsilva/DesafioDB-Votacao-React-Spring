package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.model.dto.LoginRequest;
import com.db.dbpautasbackend.model.dto.LoginResponse;
import com.db.dbpautasbackend.model.dto.RegistrarPautaRequest;
import com.db.dbpautasbackend.fixture.LoginRequestFixture;
import com.db.dbpautasbackend.fixture.RegistrarPautaRequestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PautaControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Dado uma pauta válida, quando registrada com sucesso no banco de dados, deve retornar verdadeiro")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts =  "/db/clear_pautas.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    void registrarTest(){
        LoginRequest login = LoginRequestFixture.buiderDefault();
        HttpEntity<LoginRequest> requisicaoLogin = new HttpEntity<>(login);

        ResponseEntity<LoginResponse> respostaLogin = restTemplate.postForEntity("http://localhost:" + port + "/login", requisicaoLogin, LoginResponse.class);
        String token = respostaLogin.getBody().token();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        RegistrarPautaRequest pautaDTO = RegistrarPautaRequestFixture.builderDefault();
        HttpEntity<RegistrarPautaRequest> requisicao = new HttpEntity<>(pautaDTO, httpHeaders);

        ResponseEntity<Boolean> resposta = restTemplate.postForEntity("http://localhost:" + port + "/pautas", requisicao, Boolean.class);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(Boolean.TRUE, resposta.getBody());
    }

}
