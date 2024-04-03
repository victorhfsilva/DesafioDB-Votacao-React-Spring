package com.db.dbpautasbackend.service.interfaces;

public interface TokenService {
    String gerarToken(String cpf);
    boolean isTokenValido(String token);
    String extrairSujeito(String token);
}
