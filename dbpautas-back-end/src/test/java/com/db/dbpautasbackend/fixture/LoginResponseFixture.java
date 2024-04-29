package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.model.dto.LoginResponse;
import com.db.dbpautasbackend.model.enums.Papel;

public interface LoginResponseFixture {
    static LoginResponse buiderDefault() {
            return builder().build();
    }

    private static LoginResponse.LoginResponseBuilder builder(){
        return LoginResponse.builder().token("token")
                                        .papel(Papel.ADMIN);
    }
}
