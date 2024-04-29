package com.db.dbpautasbackend.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Decisao {
    APROVADO("Aprovado"),
    EMPATE("Empate"),
    REPROVADO("Reprovado");

    private final String descricao;
}
