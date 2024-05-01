package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.model.dto.RegistrarUsuarioRequest;
import com.db.dbpautasbackend.model.enums.Papel;

public interface RegistrarUsuarioRequestFixture {

    static RegistrarUsuarioRequest builderDefault(){
        return builder().build();
    }

    static RegistrarUsuarioRequest builderComSenhaFraca() {
        return builder().senha("password123")
                .build();
    }

    static RegistrarUsuarioRequest builderComCpfInvalido() {
        return builder().cpf("12345678900")
                .build();
    }
    private static RegistrarUsuarioRequest.RegistrarUsuarioRequestBuilder builder(){
        return RegistrarUsuarioRequest.builder()
                .nome("Maria")
                .sobrenome("Silveira")
                .email("mariaSilveira@email.com")
                .cpf("41362512540")
                .senha("P@ssword1")
                .papel(Papel.ADMIN);
    }
}
