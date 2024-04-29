package com.db.dbpautasbackend.model.dto;

import com.db.dbpautasbackend.model.enums.Papel;
import lombok.Builder;

@Builder
public record LoginResponse(
        String token,
        Papel papel){
}
