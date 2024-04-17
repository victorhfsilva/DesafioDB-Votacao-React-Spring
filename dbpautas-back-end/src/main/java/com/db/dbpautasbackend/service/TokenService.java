package com.db.dbpautasbackend.service;

public interface TokenService {
    String gerarToken(String cpf);
    boolean isTokenValido(String token);
    String extrairSujeito(String token);
    String extrairToken(String authorizationHeader);
}
