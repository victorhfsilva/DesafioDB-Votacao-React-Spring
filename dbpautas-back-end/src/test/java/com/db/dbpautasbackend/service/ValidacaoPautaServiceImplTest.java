package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.exception.PautaAbertaException;
import com.db.dbpautasbackend.exception.PautaFechadaException;
import com.db.dbpautasbackend.exception.PautaFinalizadaException;
import com.db.dbpautasbackend.exception.VotoInvalidoException;
import com.db.dbpautasbackend.fixture.PautaFixture;
import com.db.dbpautasbackend.fixture.UsuarioFixture;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.service.impl.ValidacaoPautaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ValidacaoPautaServiceImplTest {

    @InjectMocks
    private ValidacaoPautaServiceImpl validacaoPautaService;

    @Test
    @DisplayName("Dado uma pauta aberta, quando validada se aberta, não deve lançar exceção.")
    void validarPautaAbertaValidaTest() {
        Pauta pautaAberta = PautaFixture.builderDePautaAberta();
        assertDoesNotThrow(() ->
                validacaoPautaService.validaPautaAberta(pautaAberta)
        );
    }

    @Test
    @DisplayName("Dado uma pauta fechada, quando validada se aberta, deve lançar exceção.")
    void validarPautaAbertaInvalidaTest() {
        Pauta pautaFechada = PautaFixture.builderDefault();
        assertThrows(PautaFechadaException.class, () ->
                validacaoPautaService.validaPautaAberta(pautaFechada)
        );
    }

    @Test
    @DisplayName("Dado uma pauta fechada, quando validada se fechada, não deve lançar exceção.")
    void validarPautaFechadaValidaTest() {
        Pauta pautaFechada = PautaFixture.builderDefault();
        assertDoesNotThrow(() ->
                validacaoPautaService.validaPautaFechada(pautaFechada)
        );
    }

    @Test
    @DisplayName("Dado uma pauta fechada, quando validada se aberta, deve lançar exceção.")
    void validarPautaFechadaInvalidaTest() {
        Pauta pautaAberta = PautaFixture.builderDePautaAberta();
        assertThrows(PautaAbertaException.class, () ->
                validacaoPautaService.validaPautaFechada(pautaAberta)
        );
    }

    @Test
    @DisplayName("Dado uma pauta finalizada, quando validada, deve lançar exceção.")
    void validarPautaFinalizadaTest() {
        Pauta pautaFinalizada = PautaFixture.builderDePautaFinalizada();
        assertThrows(PautaFinalizadaException.class, () ->
                validacaoPautaService.validaPautaFinalizada(pautaFinalizada)
        );
    }

    @Test
    @DisplayName("Dado uma pauta que o usário já votou, quando validado se este já votou, deve lançar exceção.")
    void validarSeUsuarioJaVotouTest() {
        Usuario usuario = UsuarioFixture.builderDefault();
        Pauta pauta = PautaFixture.builderDePautaAbertaComVotos(usuario);
        assertThrows(VotoInvalidoException.class, () ->
                validacaoPautaService.validaSePrimeiroVoto(pauta, usuario)
        );
    }

    @Test
    @DisplayName("Dado uma pauta que o usário ainda não votou, quando validado se este já votou, não deve lançar exceção.")
    void validarSeAindaNaoVotouTest() {
        Usuario usuario = UsuarioFixture.builderDefault();
        Pauta pauta = PautaFixture.builderDePautaAberta();
        assertDoesNotThrow(() ->
                validacaoPautaService.validaSePrimeiroVoto(pauta, usuario)
        );
    }
}
