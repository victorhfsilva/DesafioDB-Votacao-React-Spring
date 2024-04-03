package com.db.dbpautasbackend.info;

import com.db.dbpautasbackend.enums.Papel;
import com.db.dbpautasbackend.info.interfaces.UserDetailsInfo;
import lombok.Builder;

@Builder
public class UserDetailsInfoImpl implements UserDetailsInfo {

    private String cpf;
    private String senha;
    private Papel papel;

    @Override
    public String getCpf() {
        return this.cpf;
    }

    @Override
    public String getSenha() {
        return this.senha;
    }

    @Override
    public Papel getPapel() {
        return this.papel;
    }
}
