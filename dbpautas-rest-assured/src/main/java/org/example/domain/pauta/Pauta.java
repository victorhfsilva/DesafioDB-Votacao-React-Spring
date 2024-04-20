package org.example.domain.pauta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Pauta {
    String titulo;
    String resumo;
    String descricao;
    String categoria;
}
