package com.db.dbpautasbackend.mapper;

import com.db.dbpautasbackend.dto.PautaEmAndamentoDTO;
import com.db.dbpautasbackend.dto.PautaFinalizadaDTO;
import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.service.interfaces.ContabilizacaoService;
import org.springframework.data.domain.Page;

public interface PautaMapper {

    static Pauta mapRegistrarPautaDTOtoPauta(RegistrarPautaDTO registrarPautaDTO){
        return Pauta.builder().titulo(registrarPautaDTO.titulo())
                                .resumo(registrarPautaDTO.resumo())
                                .descricao(registrarPautaDTO.descricao())
                                .categoria(registrarPautaDTO.categoria())
                                .build();
    }

    static Page<PautaEmAndamentoDTO> mapPageOfPautaToPageOfPautaEmAndamentoDTO(Page<Pauta> pautas){
        return pautas.map(pauta ->
            PautaEmAndamentoDTO.builder().id(pauta.getId())
                    .titulo(pauta.getTitulo())
                    .resumo(pauta.getResumo())
                    .descricao(pauta.getDescricao())
                    .categoria(pauta.getCategoria())
                    .build()
        );
    }

    static Page<PautaFinalizadaDTO> mapPageOfPautaToPageOfPautaFinalizadaDTO(Page<Pauta> pautas, ContabilizacaoService contabilizacaoService){
        return pautas.map(pauta ->
            PautaFinalizadaDTO.builder().id(pauta.getId())
                        .titulo(pauta.getTitulo())
                        .resumo(pauta.getResumo())
                        .descricao(pauta.getDescricao())
                        .categoria(pauta.getCategoria())
                        .votosSim(pauta.getVotosSim())
                        .votosNao(pauta.getVotosNao())
                        .decisao(contabilizacaoService.contabilizar(pauta))
                        .build()
        );
    }


}
