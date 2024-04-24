package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.dto.PautaEmAndamentoDTO;
import com.db.dbpautasbackend.enums.Categoria;

public interface PautaEmAndamentoDTOFixture {
    static PautaEmAndamentoDTO builderDefault(){
        return builder().build();
    }

    private static PautaEmAndamentoDTO.PautaEmAndamentoDTOBuilder builder(){
        return PautaEmAndamentoDTO.builder().id(1L)
                .titulo("Título da Pauta")
                .resumo("Resumo da Pauta")
                .descricao("Descrição da Pauta")
                .categoria(Categoria.EDUCACAO);
    }
}
