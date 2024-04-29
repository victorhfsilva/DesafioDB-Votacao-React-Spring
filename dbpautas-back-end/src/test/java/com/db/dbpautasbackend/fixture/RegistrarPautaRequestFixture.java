package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.model.dto.RegistrarPautaRequest;
import com.db.dbpautasbackend.model.enums.Categoria;

public interface RegistrarPautaRequestFixture {

    static RegistrarPautaRequest builderDefault(){
        return builder().build();
    }

    private static RegistrarPautaRequest.RegistrarPautaRequestBuilder builder(){
        return RegistrarPautaRequest.builder().titulo("Título da pauta")
                .resumo("Resumo da pauta")
                .descricao("Descrição da pauta")
                .categoria(Categoria.EDUCACAO);
    }
}
