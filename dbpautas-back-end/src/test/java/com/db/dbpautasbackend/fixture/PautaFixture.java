package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.model.Pauta;

import java.time.LocalDateTime;

public interface PautaFixture {

    static Pauta builderDefault(){
        return builder().build();
    }

    static Pauta builderDePautaAberta(){
        return builder().aberta(true)
                .abertoAs(LocalDateTime.of(2024,4,1,18,0,12))
                .tempoDeSessaoEmMinutos(180)
                .build();
    }

    private static Pauta.PautaBuilder builder(){
        return Pauta.builder().titulo("Título da Pauta")
                                .resumo("Resumo da Pauta")
                                .descricao("Descrição da Pauta")
                                .categoria(Categoria.EDUCACAO);
    }
}
