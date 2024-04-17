package com.db.dbpautasbackend.service.impl;

import com.db.dbpautasbackend.dto.LoginDTO;
import com.db.dbpautasbackend.dto.LoginRespostaDTO;
import com.db.dbpautasbackend.enums.Papel;
import com.db.dbpautasbackend.service.LoginService;
import com.db.dbpautasbackend.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @Override
    public LoginRespostaDTO gerarToken(LoginDTO login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.cpf(), login.senha());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
        String token = tokenService.gerarToken(login.cpf());
        Papel papel = authorities.stream().findFirst().map(authority -> Papel.valueOf(authority.getAuthority())).orElseThrow();
        return LoginRespostaDTO.builder()
                                .token(token)
                                .papel(papel)
                                .build();
    }
}
