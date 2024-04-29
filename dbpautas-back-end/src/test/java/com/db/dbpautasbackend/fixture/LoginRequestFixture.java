package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.model.dto.LoginRequest;

public interface LoginRequestFixture {

    static LoginRequest buiderDefault() {
        return builder().build();
    }

    static LoginRequest builderComCpfBranco(){
        return builder().cpf("")
                        .build();
    }

    static LoginRequest builderComSenhaBranca(){
        return builder().senha("")
                        .build();
    }

    private static LoginRequest.LoginRequestBuilder builder(){
        return LoginRequest.builder().cpf("15333737525")
                                    .senha("P@ssword1");
    }

}
