package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.fixture.RegistrarPautaDTOFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PautaControllerTI {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Dado uma pauta válida, quando registrada com sucesso no banco de dados, deve retornar verdadeiro")
    @SqlGroup({
            @Sql(scripts =  "/db/clear_pautas.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    void registrarTest(){
        RegistrarPautaDTO pautaDTO = RegistrarPautaDTOFixture.builderDefault();
        HttpEntity<RegistrarPautaDTO> requisicao = new HttpEntity<>(pautaDTO);
        ResponseEntity<Boolean> resposta = restTemplate.postForEntity("http://localhost:" + port + "/pauta/registrar", requisicao, Boolean.class);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(Boolean.TRUE, resposta.getBody());
    }

}
