package com.db.dbpautasbackend.model.dto;

import com.db.dbpautasbackend.model.enums.Categoria;
import com.db.dbpautasbackend.model.enums.Decisao;
import lombok.Builder;

@Builder
public record PautaFinalizadaResponse(
        Long id, String titulo,
        String resumo,
        String descricao,
        Categoria categoria,
        int votosSim,
        int votosNao,
        Decisao decisao) {
}
