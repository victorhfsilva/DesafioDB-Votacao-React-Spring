package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.dto.RegistrarUsuarioDTO;
import com.db.dbpautasbackend.enums.Papel;
import com.db.dbpautasbackend.model.Usuario;

public interface RegistrarUsuarioDTOFixture {

    static RegistrarUsuarioDTO builderDefault(){
        return builder().build();
    }

    static RegistrarUsuarioDTO builderComSenhaFraca() {
        return builder().senha("password123")
                .build();
    }

    static RegistrarUsuarioDTO builderComCpfInvalido() {
        return builder().cpf("12345678900")
                .build();
    }
    private static RegistrarUsuarioDTO.RegistrarUsuarioDTOBuilder builder(){
        return RegistrarUsuarioDTO.builder()
                .nome("Adalberto")
                .sobrenome("Silva")
                .email("adalberto@email.com")
                .cpf("15333737525")
                .senha("P@ssword1")
                .papel(Papel.ADMIN);
    }
}
