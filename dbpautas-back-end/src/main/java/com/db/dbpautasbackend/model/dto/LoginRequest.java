package com.db.dbpautasbackend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequest(
        @NotBlank
        String cpf,
        @NotBlank
        String senha) {
}
