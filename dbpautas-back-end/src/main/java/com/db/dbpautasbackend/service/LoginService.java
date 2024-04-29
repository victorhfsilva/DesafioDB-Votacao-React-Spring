package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.model.dto.LoginRequest;
import com.db.dbpautasbackend.model.dto.LoginResponse;

public interface LoginService {
    LoginResponse gerarToken(LoginRequest loginDTO);
}
