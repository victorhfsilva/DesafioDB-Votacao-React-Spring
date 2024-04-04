package com.db.dbpautasbackend.mapper;

import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.model.Pauta;

public interface PautaMapper {
    static Pauta mapRegistrarPautaDTOtoPauta(RegistrarPautaDTO registrarPautaDTO){
        return Pauta.builder().titulo(registrarPautaDTO.titulo())
                                .resumo(registrarPautaDTO.resumo())
                                .descricao(registrarPautaDTO.descricao())
                                .categoria(registrarPautaDTO.categoria())
                                .build();
    }
}
