package com.db.dbpautasbackend.model.info;

import com.db.dbpautasbackend.model.enums.Papel;

public interface UserDetailsInfo {
    String getCpf();
    String getSenha();
    Papel getPapel();
}
