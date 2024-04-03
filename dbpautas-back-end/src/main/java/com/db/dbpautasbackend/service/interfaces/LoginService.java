package com.db.dbpautasbackend.service.interfaces;

import com.db.dbpautasbackend.dto.LoginDTO;

public interface LoginService {
    String gerarToken(LoginDTO loginDTO);
}
