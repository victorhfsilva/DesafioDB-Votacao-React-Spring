package com.db.dbpautasbackend.mapper;

import com.db.dbpautasbackend.fixture.PautaFixture;
import com.db.dbpautasbackend.fixture.RegistrarPautaRequestFixture;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.dto.PautaEmAndamentoResponse;
import com.db.dbpautasbackend.model.dto.PautaFinalizadaResponse;
import com.db.dbpautasbackend.model.dto.RegistrarPautaRequest;
import com.db.dbpautasbackend.model.enums.Decisao;
import com.db.dbpautasbackend.service.PautaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PautaMapperTest {

    @Mock
    private PautaService pautaService;


    @Test
    @DisplayName("Teste do método de mapear RegistrarPautaRequest para Pauta")
    void testMapRegistrarPautaRequestToPauta() {
        RegistrarPautaRequest registrarPautaRequest = RegistrarPautaRequestFixture.builderDefault();
        Pauta pauta = PautaMapper.mapRegistrarPautaRequesttoPauta(registrarPautaRequest);

        assertEquals(registrarPautaRequest.titulo(), pauta.getTitulo());
        assertEquals(registrarPautaRequest.resumo(), pauta.getResumo());
        assertEquals(registrarPautaRequest.descricao(), pauta.getDescricao());
        assertEquals(registrarPautaRequest.categoria(), pauta.getCategoria());
    }

    @Test
    @DisplayName("Teste do método de mapear Pauta para RegistrarPautaRequest")
    void testMapPautaToRegistrarPautaRequest() {
        Pauta pauta = PautaFixture.builderDefault();
        RegistrarPautaRequest registrarPautaRequest = PautaMapper.mapPautaToRegistrarPautaRequest(pauta);

        assertEquals(pauta.getTitulo(), registrarPautaRequest.titulo());
        assertEquals(pauta.getResumo(), registrarPautaRequest.resumo());
        assertEquals(pauta.getDescricao(), registrarPautaRequest.descricao());
        assertEquals(pauta.getCategoria(), registrarPautaRequest.categoria());
    }

    @Test
    @DisplayName("Teste do método de mapear lista de Pauta para lista de PautaEmAndamentoResponse")
    void testMapListOfPautaToListOfPautaEmAndamentoResponse() {
        Pauta pauta = PautaFixture.builderDefault();
        List<Pauta> pautas = List.of(pauta);
        List<PautaEmAndamentoResponse> pautasEmAndamento = PautaMapper.mapListOfPautaToListOfPautaEmAndamentoResponse(pautas);

        assertEquals(pautas.size(), pautasEmAndamento.size());
        pautasEmAndamento.forEach(pautaEmAndamento -> {
            assertEquals(pauta.getId(), pautaEmAndamento.id());
            assertEquals(pauta.getTitulo(), pautaEmAndamento.titulo());
            assertEquals(pauta.getResumo(), pautaEmAndamento.resumo());
            assertEquals(pauta.getDescricao(), pautaEmAndamento.descricao());
            assertEquals(pauta.getCategoria(), pautaEmAndamento.categoria());
        });
    }

    @Test
    @DisplayName("Teste do método de mapear lista de Pauta para lista de PautaFinalizadaResponse")
    void testMapListOfPautaToListOfPautaFinalizadaResponse() {
        Pauta pauta = PautaFixture.builderDefault();
        List<Pauta> pautas = List.of(pauta);

        when(pautaService.contabilizar(pauta)).thenReturn(Decisao.APROVADO);

        List<PautaFinalizadaResponse> pautasFinalizadas = PautaMapper.mapListOfPautaToListOfPautaFinalizadaResponse(pautas, pautaService);

        assertEquals(pautas.size(), pautasFinalizadas.size());
        pautasFinalizadas.forEach(pautaFinalizada -> {
            assertEquals(pauta.getId(), pautaFinalizada.id());
            assertEquals(pauta.getTitulo(), pautaFinalizada.titulo());
            assertEquals(pauta.getResumo(), pautaFinalizada.resumo());
            assertEquals(pauta.getDescricao(), pautaFinalizada.descricao());
            assertEquals(pauta.getCategoria(), pautaFinalizada.categoria());
            assertEquals(pauta.getVotosSim(), pautaFinalizada.votosSim());
            assertEquals(pauta.getVotosNao(), pautaFinalizada.votosNao());
        });
    }
}
