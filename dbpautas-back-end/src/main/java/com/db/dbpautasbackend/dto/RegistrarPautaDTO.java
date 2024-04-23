package com.db.dbpautasbackend.dto;

import com.db.dbpautasbackend.enums.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegistrarPautaDTO(
        @NotBlank
        @Size(max = 255)
        String titulo,
        @NotBlank
        @Size(max = 500)
        String resumo,
        @NotBlank
        String descricao,
        @NotNull
        Categoria categoria) {
}
