package org.example.model.pauta;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PautaEmAndamentoResposta {
    private Long id;
    private String titulo;
    private String resumo;
    private String descricao;
    private String categoria;
}
