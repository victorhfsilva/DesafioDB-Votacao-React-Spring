package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.dto.LoginDTO;
import com.db.dbpautasbackend.service.interfaces.LoginService;
import com.db.dbpautasbackend.service.interfaces.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @Override
    public String gerarToken(LoginDTO login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.cpf(), login.senha());
        this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return tokenService.gerarToken(login.cpf());
    }
}
