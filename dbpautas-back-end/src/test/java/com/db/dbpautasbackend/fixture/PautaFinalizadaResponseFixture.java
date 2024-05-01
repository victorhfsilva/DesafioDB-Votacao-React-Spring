package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.model.dto.PautaFinalizadaResponse;
import com.db.dbpautasbackend.model.enums.Categoria;
import com.db.dbpautasbackend.model.enums.Decisao;

public interface PautaFinalizadaResponseFixture {
    static PautaFinalizadaResponse builderDefault(){
        return builder().build();
    }

    private static PautaFinalizadaResponse.PautaFinalizadaResponseBuilder builder(){
        return PautaFinalizadaResponse.builder().id(1L)
                .titulo("Título da Pauta")
                .resumo("Resumo da Pauta")
                .descricao("Descrição da Pauta")
                .categoria(Categoria.EDUCACAO)
                .votosSim(99)
                .votosNao(17)
                .decisao(Decisao.APROVADO);
    }
}
