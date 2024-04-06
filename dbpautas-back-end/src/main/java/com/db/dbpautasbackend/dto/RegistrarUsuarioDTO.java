package com.db.dbpautasbackend.dto;

import com.db.dbpautasbackend.enums.Papel;
import com.db.dbpautasbackend.validators.ValidPassword;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

@Builder
public record RegistrarUsuarioDTO(
        @NotBlank
        @Size(max = 50)
        String nome,
        @NotBlank
        @Size(max = 50)
        String sobrenome,
        @Email
        @Size(max = 100)
        String email,
        @CPF
        String cpf,
        @ValidPassword
        String senha,
        @NotNull
        Papel papel) {
}
