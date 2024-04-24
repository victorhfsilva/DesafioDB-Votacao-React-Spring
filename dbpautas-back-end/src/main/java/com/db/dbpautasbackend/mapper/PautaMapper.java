package com.db.dbpautasbackend.mapper;

import com.db.dbpautasbackend.dto.PautaEmAndamentoDTO;
import com.db.dbpautasbackend.dto.PautaFinalizadaDTO;
import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.service.PautaService;

import java.util.List;

public interface PautaMapper {

    static Pauta mapRegistrarPautaDTOtoPauta(RegistrarPautaDTO registrarPautaDTO){
        return Pauta.builder()
                .titulo(registrarPautaDTO.titulo())
                .resumo(registrarPautaDTO.resumo())
                .descricao(registrarPautaDTO.descricao())
                .categoria(registrarPautaDTO.categoria())
                .build();
    }

    static List<PautaEmAndamentoDTO> mapListOfPautaToListOfPautaEmAndamentoDTO(List<Pauta> pautas){
        return pautas.stream()
                .map(pauta -> PautaEmAndamentoDTO.builder().id(pauta.getId())
                    .titulo(pauta.getTitulo())
                    .resumo(pauta.getResumo())
                    .descricao(pauta.getDescricao())
                    .categoria(pauta.getCategoria())
                    .build()
        ).toList();
    }

    static List<PautaFinalizadaDTO> mapListOfPautaToListOfPautaFinalizadaDTO(List<Pauta> pautas, PautaService pautaService){
        return pautas.stream().map(pauta -> PautaFinalizadaDTO.builder().id(pauta.getId())
                        .titulo(pauta.getTitulo())
                        .resumo(pauta.getResumo())
                        .descricao(pauta.getDescricao())
                        .categoria(pauta.getCategoria())
                        .votosSim(pauta.getVotosSim())
                        .votosNao(pauta.getVotosNao())
                        .decisao(pautaService.contabilizar(pauta))
                        .build()
        ).toList();
    }


}
