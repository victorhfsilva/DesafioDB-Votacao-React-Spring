package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.enums.Categoria;

public interface RegistrarPautaDTOFixture {

    static RegistrarPautaDTO builderDefault(){
        return builder().build();
    }

    private static RegistrarPautaDTO.RegistrarPautaDTOBuilder builder(){
        return RegistrarPautaDTO.builder().titulo("Título da pauta")
                .resumo("Resumo da pauta")
                .descricao("Descrição da pauta")
                .categoria(Categoria.EDUCACAO);
    }
}
