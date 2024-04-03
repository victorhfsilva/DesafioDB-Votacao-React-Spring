package com.db.dbpautasbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginDTO(@NotBlank String cpf, @NotBlank String senha) {
}
