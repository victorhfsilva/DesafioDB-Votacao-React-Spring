package com.db.dbpautasbackend.dto;

import com.db.dbpautasbackend.enums.Situacao;

public record CpfDTO(
    boolean status,
    Situacao situacao
){

}
