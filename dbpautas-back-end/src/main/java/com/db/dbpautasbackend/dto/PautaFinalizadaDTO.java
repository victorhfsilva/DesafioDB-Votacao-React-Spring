package com.db.dbpautasbackend.dto;

import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.enums.Decisao;
import lombok.Builder;

@Builder
public record PautaFinalizadaDTO(Long id, String titulo, String resumo, String descricao, Categoria categoria, int votosSim, int votosNao, Decisao decisao) {
}
