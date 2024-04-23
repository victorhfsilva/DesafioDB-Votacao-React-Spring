package com.db.dbpautasbackend.dto;

import com.db.dbpautasbackend.enums.Papel;
import lombok.Builder;

@Builder
public record LoginRespostaDTO(
        String token,
        Papel papel){
}
