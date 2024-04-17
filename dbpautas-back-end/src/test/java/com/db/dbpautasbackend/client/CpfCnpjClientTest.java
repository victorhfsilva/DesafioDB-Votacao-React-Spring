package com.db.dbpautasbackend.client;

import com.db.dbpautasbackend.dto.CpfDTO;
import com.db.dbpautasbackend.enums.Situacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CpfCnpjClientTest {

    @Autowired
    private CpfCnpjClient cpfCnpjClient;

    @Test
    @DisplayName("Dado um Cpf, quando buscada a situação na api, deve retornar que a api está num estado ativo.")
    void validarCpfIrregularTest() {
        String cpf = "95098681324";
        CpfDTO cpfDTO = cpfCnpjClient.getCpfCnpj(cpf);
        assertTrue(cpfDTO.status());
    }

}
