package com.db.dbpautasbackend.enums;

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
