package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.client.CpfCnpjClient;
import com.db.dbpautasbackend.model.dto.CpfResponse;
import com.db.dbpautasbackend.model.enums.Situacao;
import com.db.dbpautasbackend.exception.CpfIrregularException;
import com.db.dbpautasbackend.service.impl.ValidacaoUsuarioServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidacaoUsuarioServicoImplTest {

    @InjectMocks
    private ValidacaoUsuarioServiceImpl validacaoUsuarioService;

    @Mock
    private CpfCnpjClient cpfCnpjClient;
    @Mock
    private Environment environment;

    @Test
    @DisplayName("Dado uma cpf regular, quando validado, não deve lançar exceção.")
    void validarCpfRegularTest() {
        when(environment.getProperty("validation.cpf.active")).thenReturn("true");
        when(cpfCnpjClient.getCpfCnpj("95098681324")).thenReturn(new CpfResponse(true, Situacao.REGULAR));
        assertDoesNotThrow(() ->
                validacaoUsuarioService.validarSituacaoRegularDoCpf("95098681324")
        );
    }

    @Test
    @DisplayName("Dado uma cpf irregular, quando validado, deve lançar exceção.")
    void validarCpfIrregularTest() {
        when(environment.getProperty("validation.cpf.active")).thenReturn("true");
        when(cpfCnpjClient.getCpfCnpj("95098681324")).thenReturn(new CpfResponse(true, Situacao.CANCELADA));
        assertThrows(CpfIrregularException.class, () ->
                validacaoUsuarioService.validarSituacaoRegularDoCpf("95098681324")
        );
    }
}
