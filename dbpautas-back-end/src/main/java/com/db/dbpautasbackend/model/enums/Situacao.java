package com.db.dbpautasbackend.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Situacao {
    REGULAR,
    CANCELADA,
    SUSPENSA,
    PENDENTE,
    NULA;

    @JsonCreator
    public static Situacao fromString(String value) {
        return Situacao.valueOf(value.toUpperCase());
    }
}
