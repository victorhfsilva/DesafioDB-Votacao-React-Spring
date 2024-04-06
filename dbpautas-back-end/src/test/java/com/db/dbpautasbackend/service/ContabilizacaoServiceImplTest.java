package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.enums.Decisao;
import com.db.dbpautasbackend.fixture.PautaFixture;
import com.db.dbpautasbackend.model.Pauta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ContabilizacaoServiceImplTest {

    @InjectMocks
    private ContabilizacaoServiceImpl contabilizacaoService;


    @ParameterizedTest
    @MethodSource("pautas")
    @DisplayName("Dado pautas finalizadas, quando contabilizado os votos, deve retornar a decisão correta.")
    void contabilizarTest(Pauta pauta, Decisao decisaoEsperada){
        Decisao decisaoObtida = contabilizacaoService.contabilizar(pauta);
        assertEquals(decisaoEsperada, decisaoObtida);
    }

    public static Stream<Arguments> pautas() {
        return Stream.of(
                Arguments.of(PautaFixture.builderDePautaAprovada(), Decisao.APROVADO),
                Arguments.of(PautaFixture.builderDePautaReprovada(), Decisao.REPROVADO),
                Arguments.of(PautaFixture.builderDePautaEmpatada(), Decisao.EMPATE)
        );
    }

}
