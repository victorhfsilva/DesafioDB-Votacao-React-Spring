package com.db.dbpautasbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Voto {
    SIM(true),
    NAO(false);

    private boolean sim;
}
