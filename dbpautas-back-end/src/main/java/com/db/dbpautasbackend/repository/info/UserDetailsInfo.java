package com.db.dbpautasbackend.repository.info;

import com.db.dbpautasbackend.enums.Papel;

public interface UserDetailsInfo {
    String getCpf();
    String getSenha();
    Papel getPapel();
}
