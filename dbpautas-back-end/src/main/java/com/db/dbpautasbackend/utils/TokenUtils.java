package com.db.dbpautasbackend.utils;

import com.db.dbpautasbackend.exception.FalhaNaAutenticacaoException;
import com.db.dbpautasbackend.service.interfaces.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TokenUtils {

    private TokenService tokenService;

    public String extrairToken(String authorizationHeader) {
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        } else {
            return "";
        }
    }

    public String validarToken(String headerAutorizacao){
        String token = extrairToken(headerAutorizacao);
        if (!tokenService.isTokenValido(token)){
            throw new FalhaNaAutenticacaoException("Token Inválido.");
        }
        return token;
    }
}

