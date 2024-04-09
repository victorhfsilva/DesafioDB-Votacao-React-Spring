package com.db.dbpautasbackend.service.interfaces;

import com.db.dbpautasbackend.dto.LoginDTO;
import com.db.dbpautasbackend.dto.LoginRespostaDTO;

public interface LoginService {
    LoginRespostaDTO gerarToken(LoginDTO loginDTO);
}
