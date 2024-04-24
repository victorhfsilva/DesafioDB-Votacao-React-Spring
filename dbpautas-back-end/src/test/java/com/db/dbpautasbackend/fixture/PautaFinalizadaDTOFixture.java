package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.dto.PautaEmAndamentoDTO;
import com.db.dbpautasbackend.dto.PautaFinalizadaDTO;
import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.enums.Decisao;

public class PautaFinalizadaDTOFixture {
    public static PautaFinalizadaDTO builderDefault(){
        return builder().build();
    }

    private static PautaFinalizadaDTO.PautaFinalizadaDTOBuilder builder(){
        return PautaFinalizadaDTO.builder().id(1L)
                .titulo("Título da Pauta")
                .resumo("Resumo da Pauta")
                .descricao("Descrição da Pauta")
                .categoria(Categoria.EDUCACAO)
                .votosSim(99)
                .votosNao(17)
                .decisao(Decisao.APROVADO);
    }
}
