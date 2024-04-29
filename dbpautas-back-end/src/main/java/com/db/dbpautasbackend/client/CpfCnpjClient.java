package com.db.dbpautasbackend.client;

import com.db.dbpautasbackend.model.dto.CpfResponse;

public interface CpfCnpjClient {
        CpfResponse getCpfCnpj(String cpf);
}
