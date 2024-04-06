package com.db.dbpautasbackend.mapper;

import com.db.dbpautasbackend.dto.PautaEmAndamentoDTO;
import com.db.dbpautasbackend.dto.PautaFinalizadaDTO;
import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.enums.Decisao;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.service.interfaces.ContabilizacaoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

public interface PautaMapper {

    public static Pauta mapRegistrarPautaDTOtoPauta(RegistrarPautaDTO registrarPautaDTO){
        return Pauta.builder().titulo(registrarPautaDTO.titulo())
                                .resumo(registrarPautaDTO.resumo())
                                .descricao(registrarPautaDTO.descricao())
                                .categoria(registrarPautaDTO.categoria())
                                .build();
    }

    public static Page<PautaEmAndamentoDTO> mapPageOfPautaToPageOfPautaEmAndamentoDTO(Page<Pauta> pautas){
        return pautas.map(pauta ->
            PautaEmAndamentoDTO.builder().id(pauta.getId())
                    .titulo(pauta.getTitulo())
                    .resumo(pauta.getResumo())
                    .descricao(pauta.getDescricao())
                    .categoria(pauta.getCategoria())
                    .build()
        );
    }

    public static Page<PautaFinalizadaDTO> mapPageOfPautaToPageOfPautaFinalizadaDTO(Page<Pauta> pautas, ContabilizacaoService contabilizacaoService){
        return pautas.map(pauta -> {
            return PautaFinalizadaDTO.builder().id(pauta.getId())
                        .titulo(pauta.getTitulo())
                        .resumo(pauta.getResumo())
                        .descricao(pauta.getDescricao())
                        .categoria(pauta.getCategoria())
                        .votosSim(pauta.getVotosSim())
                        .votosNao(pauta.getVotosNao())
                        .decisao(contabilizacaoService.contabilizar(pauta))
                        .build();
        });
    }


}
