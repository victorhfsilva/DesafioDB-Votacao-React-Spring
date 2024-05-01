package com.db.dbpautasbackend.fixture;

import com.db.dbpautasbackend.model.enums.Papel;
import com.db.dbpautasbackend.model.info.impl.UserDetailsInfoImpl;

public interface UserDetailsInfoFixture {

    static UserDetailsInfoImpl builderDefault(){
        return builder().build();
    }

    private static UserDetailsInfoImpl.UserDetailsInfoImplBuilder builder(){
        return UserDetailsInfoImpl.builder()
                .cpf("15333737525")
                .senha("P@ssword1")
                .papel(Papel.ADMIN);
    }

}
