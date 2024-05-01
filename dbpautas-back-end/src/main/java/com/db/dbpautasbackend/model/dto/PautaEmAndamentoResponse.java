package com.db.dbpautasbackend.model.dto;

import com.db.dbpautasbackend.model.enums.Categoria;
import lombok.Builder;

@Builder
public record PautaEmAndamentoResponse(
        Long id,
        String titulo,
        String resumo,
        String descricao,
        Categoria categoria) {
}
