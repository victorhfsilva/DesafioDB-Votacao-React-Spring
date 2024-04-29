package com.db.dbpautasbackend.service.impl;

import com.db.dbpautasbackend.client.CpfCnpjClient;
import com.db.dbpautasbackend.model.dto.CpfResponse;
import com.db.dbpautasbackend.model.enums.Situacao;
import com.db.dbpautasbackend.exception.ClienteIndisponivelException;
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

            CpfResponse cpfDTO = cpfCnpjClient.getCpfCnpj(cpf);

            if (cpfDTO.status()){
                if (validarCpf && !cpfDTO.situacao().equals(Situacao.REGULAR)) {
                    throw new CpfIrregularException("CPF irregular.");
                }
            } else {
                throw new ClienteIndisponivelException("CpfCnpjClient indisponível.");
            }

        }
}
