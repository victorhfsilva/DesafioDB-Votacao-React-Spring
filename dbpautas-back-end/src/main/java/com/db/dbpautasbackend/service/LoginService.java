package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.dto.LoginDTO;
import com.db.dbpautasbackend.dto.LoginRespostaDTO;

public interface LoginService {
    LoginRespostaDTO gerarToken(LoginDTO loginDTO);
}
