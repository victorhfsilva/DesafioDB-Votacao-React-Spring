package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.enums.Papel;
import com.db.dbpautasbackend.model.Usuario;

public interface UsuarioFixture {

    static Usuario builderDefault(){
        return builder().build();
    }

    private static Usuario.UsuarioBuilder builder(){
        return Usuario.builder().id(1L)
                            .nome("Adalberto")
                            .sobrenome("Silva")
                            .email("adalberto@email.com")
                            .cpf("15333737525")
                            .senha("P@ssword1")
                            .papel(Papel.ADMIN);
    }

}
