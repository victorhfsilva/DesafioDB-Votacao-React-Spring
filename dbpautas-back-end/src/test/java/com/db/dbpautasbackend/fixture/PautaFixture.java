package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public interface PautaFixture {

    static Pauta builderDefault(){
        return builder().build();
    }

    static Pauta builderDePautaAberta(){
        return builder().aberta(true)
                .abertoAs(LocalDateTime.of(2024,4,1,18,0,12))
                .tempoDeSessaoEmMinutos((int) Double.POSITIVE_INFINITY)
                .build();
    }

    static Pauta builderDePautaAbertaComVotos(Usuario usuario){
        return builder().aberta(true)
                .abertoAs(LocalDateTime.of(2024,4,1,18,0,12))
                .tempoDeSessaoEmMinutos((int) Double.POSITIVE_INFINITY)
                .eleitores(List.of(usuario))
                .votosSim(1)
                .build();
    }

    static Pauta builderDePautaFechada(){
        return builder().aberta(true)
                .abertoAs(LocalDateTime.of(2024,4,1,18,0,12))
                .tempoDeSessaoEmMinutos(0)
                .build();
    }

    private static Pauta.PautaBuilder builder(){
        return Pauta.builder().id(1L)
                                .titulo("Título da Pauta")
                                .resumo("Resumo da Pauta")
                                .descricao("Descrição da Pauta")
                                .categoria(Categoria.EDUCACAO);
    }
}
