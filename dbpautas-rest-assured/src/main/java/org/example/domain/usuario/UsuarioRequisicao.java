package org.example.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UsuarioRequisicao {
        private String nome;
        private String sobrenome;
        private String cpf;
        private String email;
        private String senha;
        private String papel;
}
