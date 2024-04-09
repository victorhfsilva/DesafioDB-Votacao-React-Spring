package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.dto.LoginRespostaDTO;
import com.db.dbpautasbackend.enums.Papel;

public interface LoginRespostaDTOFixture {
    static LoginRespostaDTO buiderDefault() {
            return builder().build();
    }

    private static LoginRespostaDTO.LoginRespostaDTOBuilder builder(){
        return LoginRespostaDTO.builder().token("token")
                                        .papel(Papel.ADMIN);
    }
}
