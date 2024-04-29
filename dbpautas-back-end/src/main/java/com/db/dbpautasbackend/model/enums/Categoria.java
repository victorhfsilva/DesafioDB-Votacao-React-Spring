package com.db.dbpautasbackend.model.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Categoria {
    FINANCAS("Finanças"),
    EDUCACAO("Educação"),
    SAUDE("Saúde"),
    TECNOLOGIA("Tecnologia"),
    MEIO_AMBIENTE("Meio Ambiente"),
    TRANSPORTE("Transporte"),
    SEGURANCA_PUBLICA("Segurança Pública"),
    INFRAESTRUTURA("Infraestrutura");

    private final String descricao;
}