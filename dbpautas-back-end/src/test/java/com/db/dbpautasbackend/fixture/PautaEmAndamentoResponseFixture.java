package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.model.dto.PautaEmAndamentoResponse;
import com.db.dbpautasbackend.model.enums.Categoria;

public interface PautaEmAndamentoResponseFixture {
    static PautaEmAndamentoResponse builderDefault(){
        return builder().build();
    }

    private static PautaEmAndamentoResponse.PautaEmAndamentoResponseBuilder builder(){
        return PautaEmAndamentoResponse.builder().id(1L)
                .titulo("Título da Pauta")
                .resumo("Resumo da Pauta")
                .descricao("Descrição da Pauta")
                .categoria(Categoria.EDUCACAO);
    }
}
