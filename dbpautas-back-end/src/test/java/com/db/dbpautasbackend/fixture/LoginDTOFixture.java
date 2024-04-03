package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.dto.LoginDTO;

public interface LoginDTOFixture {

    static LoginDTO buiderDefault() {
        return builder().build();
    }

    static LoginDTO builderComCpfBranco(){
        return builder().cpf("")
                        .build();
    }

    static LoginDTO builderComSenhaBranca(){
        return builder().senha("")
                        .build();
    }

    private static LoginDTO.LoginDTOBuilder builder(){
        return LoginDTO.builder().cpf("15333737525")
                                    .senha("P@ssword1");
    }

}
