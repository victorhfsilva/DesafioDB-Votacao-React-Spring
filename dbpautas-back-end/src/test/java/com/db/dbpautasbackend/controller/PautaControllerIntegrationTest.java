package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.model.dto.*;
import com.db.dbpautasbackend.fixture.LoginRequestFixture;
import com.db.dbpautasbackend.fixture.RegistrarPautaRequestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

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

    @Test
    @DisplayName("Dadas pautas válidas, quando buscado por pautas fechadas, deve retornar as pautas corretas")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts =  "/db/clear_pautas.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
            @Sql(scripts =  "/db/insert_pautas.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void buscarPautasFechadasTest(){
        LoginRequest login = LoginRequestFixture.buiderDefault();
        HttpEntity<LoginRequest> requisicaoLogin = new HttpEntity<>(login);

        ResponseEntity<LoginResponse> respostaLogin = restTemplate.postForEntity("http://localhost:" + port + "/login", requisicaoLogin, LoginResponse.class);
        String token = respostaLogin.getBody().token();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        HttpEntity<RegistrarPautaRequest> requisicao = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<PautaEmAndamentoResponse>> resposta = restTemplate.exchange(
                "http://localhost:" + port + "/pautas/fechadas/",
                HttpMethod.GET,
                requisicao,
                new ParameterizedTypeReference<List<PautaEmAndamentoResponse>>() {}
        );
        List<PautaEmAndamentoResponse> pautas = resposta.getBody();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(2, pautas.size());
        assertEquals("Titulo da Pauta 1", pautas.get(0).titulo());
        assertEquals("Titulo da Pauta 2", pautas.get(1).titulo());
    }

    @Test
    @DisplayName("Dadas pautas válidas, quando buscado por pautas fechadas por categoria, deve retornar as pautas corretas")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts =  "/db/clear_pautas.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
            @Sql(scripts =  "/db/insert_pautas.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void buscarPautasFechadasPorCategoriaTest(){
        LoginRequest login = LoginRequestFixture.buiderDefault();
        HttpEntity<LoginRequest> requisicaoLogin = new HttpEntity<>(login);

        ResponseEntity<LoginResponse> respostaLogin = restTemplate.postForEntity("http://localhost:" + port + "/login", requisicaoLogin, LoginResponse.class);
        String token = respostaLogin.getBody().token();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        HttpEntity<RegistrarPautaRequest> requisicao = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<PautaEmAndamentoResponse>> resposta = restTemplate.exchange(
                "http://localhost:" + port + "/pautas/fechadas/FINANCAS",
                HttpMethod.GET,
                requisicao,
                new ParameterizedTypeReference<List<PautaEmAndamentoResponse>>() {}
        );
        List<PautaEmAndamentoResponse> pautas = resposta.getBody();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(1, pautas.size());
        assertEquals("Titulo da Pauta 1", pautas.get(0).titulo());
    }

    @Test
    @DisplayName("Dadas pautas válidas, quando buscado por pautas abertas, deve retornar as pautas corretas")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts =  "/db/clear_pautas.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
            @Sql(scripts =  "/db/insert_pautas.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void buscarPautasAbertasTest(){
        LoginRequest login = LoginRequestFixture.buiderDefault();
        HttpEntity<LoginRequest> requisicaoLogin = new HttpEntity<>(login);

        ResponseEntity<LoginResponse> respostaLogin = restTemplate.postForEntity("http://localhost:" + port + "/login", requisicaoLogin, LoginResponse.class);
        String token = respostaLogin.getBody().token();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        HttpEntity<RegistrarPautaRequest> requisicao = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<PautaEmAndamentoResponse>> resposta = restTemplate.exchange(
                "http://localhost:" + port + "/pautas/abertas/",
                HttpMethod.GET,
                requisicao,
                new ParameterizedTypeReference<List<PautaEmAndamentoResponse>>() {}
        );
        List<PautaEmAndamentoResponse> pautas = resposta.getBody();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(2, pautas.size());
        assertEquals("Titulo da Pauta 3", pautas.get(0).titulo());
        assertEquals("Titulo da Pauta 4", pautas.get(1).titulo());
    }

    @Test
    @DisplayName("Dadas pautas válidas, quando buscado por pautas abertas, deve retornar as pautas corretas")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts =  "/db/clear_pautas.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
            @Sql(scripts =  "/db/insert_pautas.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void buscarPautasAbertasPorCategoriaTest(){
        LoginRequest login = LoginRequestFixture.buiderDefault();
        HttpEntity<LoginRequest> requisicaoLogin = new HttpEntity<>(login);

        ResponseEntity<LoginResponse> respostaLogin = restTemplate.postForEntity("http://localhost:" + port + "/login", requisicaoLogin, LoginResponse.class);
        String token = respostaLogin.getBody().token();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        HttpEntity<RegistrarPautaRequest> requisicao = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<PautaEmAndamentoResponse>> resposta = restTemplate.exchange(
                "http://localhost:" + port + "/pautas/abertas/SAUDE",
                HttpMethod.GET,
                requisicao,
                new ParameterizedTypeReference<List<PautaEmAndamentoResponse>>() {}
        );
        List<PautaEmAndamentoResponse> pautas = resposta.getBody();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(1, pautas.size());
        assertEquals("Titulo da Pauta 3", pautas.get(0).titulo());
    }

    @Test
    @DisplayName("Dadas pautas válidas, quando buscado por pautas finalizadas, deve retornar as pautas corretas")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts =  "/db/clear_pautas.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
            @Sql(scripts =  "/db/insert_pautas.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void buscarPautasFinalizadasTest(){
        LoginRequest login = LoginRequestFixture.buiderDefault();
        HttpEntity<LoginRequest> requisicaoLogin = new HttpEntity<>(login);

        ResponseEntity<LoginResponse> respostaLogin = restTemplate.postForEntity("http://localhost:" + port + "/login", requisicaoLogin, LoginResponse.class);
        String token = respostaLogin.getBody().token();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        HttpEntity<RegistrarPautaRequest> requisicao = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<PautaFinalizadaResponse>> resposta = restTemplate.exchange(
                "http://localhost:" + port + "/pautas/finalizadas/",
                HttpMethod.GET,
                requisicao,
                new ParameterizedTypeReference<List<PautaFinalizadaResponse>>() {}
        );
        List<PautaFinalizadaResponse> pautas = resposta.getBody();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(3, pautas.size());
        assertEquals("Titulo da Pauta 5", pautas.get(0).titulo());
        assertEquals("Titulo da Pauta 6", pautas.get(1).titulo());
        assertEquals("Titulo da Pauta 7", pautas.get(2).titulo());
    }

    @Test
    @DisplayName("Dadas pautas válidas, quando buscado por pautas finalizadas por categoria, deve retornar as pautas corretas")
    @SqlGroup({
            @Sql(scripts = "/db/clear_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/db/insert_usuarios.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts =  "/db/clear_pautas.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
            @Sql(scripts =  "/db/insert_pautas.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void buscarPautasFinalizadasPorCategoriaTest(){
        LoginRequest login = LoginRequestFixture.buiderDefault();
        HttpEntity<LoginRequest> requisicaoLogin = new HttpEntity<>(login);

        ResponseEntity<LoginResponse> respostaLogin = restTemplate.postForEntity("http://localhost:" + port + "/login", requisicaoLogin, LoginResponse.class);
        String token = respostaLogin.getBody().token();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        HttpEntity<RegistrarPautaRequest> requisicao = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<PautaFinalizadaResponse>> resposta = restTemplate.exchange(
                "http://localhost:" + port + "/pautas/finalizadas/TECNOLOGIA",
                HttpMethod.GET,
                requisicao,
                new ParameterizedTypeReference<List<PautaFinalizadaResponse>>() {}
        );
        List<PautaFinalizadaResponse> pautas = resposta.getBody();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(1, pautas.size());
        assertEquals("Titulo da Pauta 7", pautas.get(0).titulo());
    }
}
