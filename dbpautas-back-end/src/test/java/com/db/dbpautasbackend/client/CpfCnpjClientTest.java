package com.db.dbpautasbackend.client;

import com.db.dbpautasbackend.dto.CpfDTO;
import com.db.dbpautasbackend.enums.Situacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CpfCnpjClientTest {

    @Autowired
    private CpfCnpjClient cpfCnpjClient;

    /*
     * Este teste só é válido utilizando o token de testes de integração que sempre retorna cpfs inválidos.
     */
    @Test
    @DisplayName("Dado um Cpf, quando buscada a situação na api, deve retornar cancelada.")
    void validarCpfIrregularTest() {
        String cpf = "95098681324";
        CpfDTO cpfDTOEsperado = new CpfDTO(true, Situacao.CANCELADA);
        CpfDTO cpfDTOObtido = cpfCnpjClient.getCpfCnpj(cpf);
        assertEquals(cpfDTOEsperado, cpfDTOObtido);
    }

}
