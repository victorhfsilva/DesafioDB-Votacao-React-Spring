package com.db.dbpautasbackend.model.info.impl;

import com.db.dbpautasbackend.model.enums.Papel;
import com.db.dbpautasbackend.model.info.UserDetailsInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
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
