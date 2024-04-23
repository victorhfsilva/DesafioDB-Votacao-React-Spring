package com.db.dbpautasbackend.client.impl;

import com.db.dbpautasbackend.client.CpfCnpjClient;
import com.db.dbpautasbackend.dto.CpfDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Repository
public class CpfCnpjClientImpl implements CpfCnpjClient {

    private final CpfCnpjClient cpfCnpjClient;

    @Override
    public CpfDTO getCpfCnpj(String cpf) {
        return cpfCnpjClient.getCpfCnpj(cpf);
    }

    @FeignClient(name = "cpf-cnpj", url = "${endpoint.cpfcnpf}")
    private interface CpfCnpjClient {
        @GetMapping("/8/{cpf}")
        CpfDTO getCpfCnpj(@PathVariable("cpf") String cpf);
    }
}
