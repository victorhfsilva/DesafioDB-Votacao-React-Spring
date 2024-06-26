package com.db.dbpautasbackend.mapper;

import com.db.dbpautasbackend.model.dto.PautaEmAndamentoResponse;
import com.db.dbpautasbackend.model.dto.PautaFinalizadaResponse;
import com.db.dbpautasbackend.model.dto.RegistrarPautaRequest;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.service.PautaService;

import java.util.List;

public interface PautaMapper {

    static Pauta mapRegistrarPautaRequesttoPauta(RegistrarPautaRequest registrarPautaDTO){
        return Pauta.builder()
                .titulo(registrarPautaDTO.titulo())
                .resumo(registrarPautaDTO.resumo())
                .descricao(registrarPautaDTO.descricao())
                .categoria(registrarPautaDTO.categoria())
                .build();
    }

    static RegistrarPautaRequest mapPautaToRegistrarPautaRequest(Pauta pauta){
        return RegistrarPautaRequest.builder()
                .titulo(pauta.getTitulo())
                .resumo(pauta.getResumo())
                .descricao(pauta.getDescricao())
                .categoria(pauta.getCategoria())
                .build();
    }

    static List<PautaEmAndamentoResponse> mapListOfPautaToListOfPautaEmAndamentoResponse(List<Pauta> pautas){
        return pautas.stream()
                .map(pauta -> PautaEmAndamentoResponse.builder().id(pauta.getId())
                    .titulo(pauta.getTitulo())
                    .resumo(pauta.getResumo())
                    .descricao(pauta.getDescricao())
                    .categoria(pauta.getCategoria())
                    .build()
        ).toList();
    }

    static List<PautaFinalizadaResponse> mapListOfPautaToListOfPautaFinalizadaResponse(List<Pauta> pautas, PautaService pautaService){
        return pautas.stream().map(pauta -> PautaFinalizadaResponse.builder().id(pauta.getId())
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
