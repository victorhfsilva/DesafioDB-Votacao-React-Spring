package com.db.dbpautasbackend.client;

import com.db.dbpautasbackend.dto.CpfDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * Este cliente está utilizando o token para testes de integração. Favor substituí-lo em produção.
 */
@FeignClient(name = "cpf-cnpj", url = "https://api.cpfcnpj.com.br/5ae973d7a997af13f0aaf2bf60e65803")
public interface CpfCnpjClient {

    @GetMapping("/8/{cpf}")
    CpfDTO getCpfCnpj(@PathVariable("cpf") String cpf);
}
