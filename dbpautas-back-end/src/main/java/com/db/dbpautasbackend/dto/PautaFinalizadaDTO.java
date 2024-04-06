package com.db.dbpautasbackend.dto;

import com.db.dbpautasbackend.enums.Categoria;
import lombok.Builder;

@Builder
public record PautaFinalizadaDTO(Long id, String titulo, String resumo, String descricao, Categoria categoria, int votosSim, int votosNao) {
}
