package com.db.dbpautasbackend.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Voto {
    SIM(true),
    NAO(false);

    private final boolean sim;
}
