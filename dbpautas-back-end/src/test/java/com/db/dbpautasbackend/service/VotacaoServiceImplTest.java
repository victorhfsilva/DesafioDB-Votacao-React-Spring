package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.exception.PautaFechadaException;
import com.db.dbpautasbackend.exception.SessaoFinalizadaException;
import com.db.dbpautasbackend.exception.VotoInvalidoException;
import com.db.dbpautasbackend.fixture.PautaFixture;
import com.db.dbpautasbackend.fixture.UsuarioFixture;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.PautaRepository;
import com.db.dbpautasbackend.service.impl.VotacaoServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotacaoServiceImplTest {

    @InjectMocks
    private VotacaoServiceImpl votacaoService;

    @Mock
    private PautaRepository pautaRepository;

    @Test
    @DisplayName("Dado uma pauta aberta e um usuario valido, quando usuário votar na pauta, deve atualizar pauta corretamente.")
    void votarEmPautaAbertaTest() {
        Usuario usuario = UsuarioFixture.builderDefault();
        Pauta pautaAberta = PautaFixture.builderDePautaAberta();
        Pauta pautaEsperada = PautaFixture.builderDePautaAbertaComVotos(usuario);
        when(pautaRepository.save(any())).thenReturn(pautaEsperada);
        Pauta pautaObtida = votacaoService.votar(pautaAberta, usuario, Voto.SIM);
        assertEquals(pautaEsperada.getVotosSim(), pautaObtida.getVotosSim());
        assertIterableEquals(pautaEsperada.getEleitores(), pautaObtida.getEleitores());
    }

    @Test
    @DisplayName("Dado uma pauta fechada, quando usuário votar na pauta, deve lançar exceção.")
    void votarEmPautaFechadaTest() {
        Usuario usuario = UsuarioFixture.builderDefault();
        Pauta pautaFechada = PautaFixture.builderDefault();
        assertThrows(PautaFechadaException.class, () ->
            votacaoService.votar(pautaFechada, usuario, Voto.SIM)
        );
    }

    @Test
    @DisplayName("Dado uma pauta fechada, quando usuário votar na pauta, deve lançar exceção.")
    void votarEmPautaFinalizadaTest() {
        Usuario usuario = UsuarioFixture.builderDefault();
        Pauta pautaFechada = PautaFixture.builderDePautaFinalizada();
        assertThrows(SessaoFinalizadaException.class, () ->
            votacaoService.votar(pautaFechada, usuario, Voto.SIM)
        );
    }

    @Test
    @DisplayName("Dado uma pauta fechada, quando usuário votar na pauta, deve lançar exceção.")
    void votarEmPautaFDuasVezesTest() {
        Usuario usuario = UsuarioFixture.builderDefault();
        Pauta pauta = PautaFixture.builderDePautaAbertaComVotos(usuario);
        assertThrows(VotoInvalidoException.class, () ->
            votacaoService.votar(pauta, usuario, Voto.SIM)
        );
    }

}
