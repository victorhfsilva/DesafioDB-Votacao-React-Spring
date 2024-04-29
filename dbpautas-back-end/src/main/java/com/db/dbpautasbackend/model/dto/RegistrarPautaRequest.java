package com.db.dbpautasbackend.model.dto;

import com.db.dbpautasbackend.model.enums.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegistrarPautaRequest(
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
