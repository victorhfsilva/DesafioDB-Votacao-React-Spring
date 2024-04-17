package com.db.dbpautasbackend.dto;

import com.db.dbpautasbackend.enums.Erro;

import java.util.Map;

public record ErroValidacaoDTO(Erro erro, Map<String, String> errors) {
}
