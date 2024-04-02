package com.db.dbpautasbackend.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(@NotBlank String cpf, @NotBlank String senha) {
}
