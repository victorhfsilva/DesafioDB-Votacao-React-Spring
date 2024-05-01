package com.db.dbpautasbackend.model.dto;

import com.db.dbpautasbackend.model.enums.Situacao;

public record CpfResponse(
        boolean status,
        Situacao situacao){

}
