package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.fixture.PautaFixture;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.repository.PautaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @InjectMocks
    private PautaServiceImpl pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Test
    @DisplayName("Dado uma pauta fechada, quando aberta com sucesso, deve retornar a pauta correta.")
    void abrirPautaTest(){
        Pauta pauta = PautaFixture.builderDefault();
        Pauta pautaAbertaEsperada = PautaFixture.builderDePautaAberta();

        when(pautaRepository.findById(anyLong())).thenReturn(Optional.of(pauta));
        when(pautaRepository.save(any())).thenReturn(pautaAbertaEsperada);

        Pauta pautaAbertaObtida = pautaService.abrirPauta(1L, pautaAbertaEsperada.getTempoDeSessaoEmMinutos());
        assertEquals(pautaAbertaEsperada.isAberta(), pautaAbertaObtida.isAberta());
    }

}
