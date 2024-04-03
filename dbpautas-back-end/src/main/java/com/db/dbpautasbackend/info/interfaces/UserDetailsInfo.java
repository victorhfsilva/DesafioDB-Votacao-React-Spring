package com.db.dbpautasbackend.info.interfaces;

import com.db.dbpautasbackend.enums.Papel;

public interface UserDetailsInfo {
    String getCpf();
    String getSenha();
    Papel getPapel();
}
