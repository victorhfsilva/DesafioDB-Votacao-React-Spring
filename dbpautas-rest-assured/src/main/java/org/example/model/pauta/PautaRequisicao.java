package org.example.model.pauta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PautaRequisicao {
    private String titulo;
    private String resumo;
    private String descricao;
    private String categoria;
}
