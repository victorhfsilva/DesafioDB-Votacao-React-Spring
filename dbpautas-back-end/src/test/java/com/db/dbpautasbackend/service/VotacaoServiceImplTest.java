package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.model.enums.Voto;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotacaoServiceImplTest {

    @InjectMocks
    private VotacaoServiceImpl votacaoService;
    @Mock
    private PautaRepository pautaRepository;
    @Mock
    private ValidacaoPautaService validacaoPautaService;


    @Test
    @DisplayName("Dado uma pauta aberta e um usuario valido, quando usuário votar na pauta, deve atualizar pauta corretamente.")
    void votarEmPautaAbertaTest() {
        Usuario usuario = UsuarioFixture.builderDefault();
        Pauta pautaAberta = PautaFixture.builderDePautaAberta();
        Pauta pautaEsperada = PautaFixture.builderDePautaAbertaComVotos(usuario);

        when(pautaRepository.save(any())).thenReturn(pautaEsperada);

        Pauta pautaObtida = votacaoService.votar(pautaAberta, usuario, Voto.SIM);

        verify(validacaoPautaService).validaPautaAberta(pautaAberta);
        verify(validacaoPautaService).validaPautaFinalizada(pautaAberta);
        verify(validacaoPautaService).validaSePrimeiroVoto(pautaAberta, usuario);

        assertEquals(pautaEsperada.getVotosSim(), pautaObtida.getVotosSim());
        assertIterableEquals(pautaEsperada.getEleitores(), pautaObtida.getEleitores());
    }

}
