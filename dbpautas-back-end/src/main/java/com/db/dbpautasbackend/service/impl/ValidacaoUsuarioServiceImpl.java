package com.db.dbpautasbackend.service.impl;

import com.db.dbpautasbackend.client.CpfCnpjClient;
import com.db.dbpautasbackend.dto.CpfDTO;
import com.db.dbpautasbackend.enums.Situacao;
import com.db.dbpautasbackend.exception.CpfIrregularException;
import com.db.dbpautasbackend.service.ValidacaoUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidacaoUsuarioServiceImpl implements ValidacaoUsuarioService {

        private CpfCnpjClient cpfCnpjClient;
        private Environment environment;

        @Override
        public void validarSituacaoRegularDoCpf(String cpf) {
            boolean validarCpf = Boolean.parseBoolean(environment.getProperty("validation.cpf.active"));
            CpfDTO cpfDDTO = cpfCnpjClient.getCpfCnpj(cpf);
            if (validarCpf && cpfDDTO.status() && !cpfDDTO.situacao().equals(Situacao.REGULAR)) {
                    throw new CpfIrregularException("CPF irregular.");
            }
        }
}
