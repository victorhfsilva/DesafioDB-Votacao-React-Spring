package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.model.dto.LoginRequest;
import com.db.dbpautasbackend.model.dto.LoginResponse;
import com.db.dbpautasbackend.model.enums.Papel;
import com.db.dbpautasbackend.fixture.LoginRequestFixture;
import com.db.dbpautasbackend.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @InjectMocks
    private LoginServiceImpl loginService;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    TokenService tokenService;

    @Test
    @DisplayName("Dado dados de login válidos, quando feito o login, deve retornar um Token.")
    void gerarTokenComLoginValidoTest(){
        LoginRequest login = LoginRequestFixture.buiderDefault();

        String token = "token";
        Papel papel = Papel.ADMIN;

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.cpf(), login.senha());

        Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(papel.name()));

        Authentication authentication = new TestingAuthenticationToken(login.cpf(), login.senha(), authorities);

        when(authenticationManager.authenticate(usernamePasswordAuthenticationToken)).thenReturn(authentication);
        when(tokenService.gerarToken(login.cpf())).thenReturn(token);

        LoginResponse loginRespostaDTO = loginService.gerarToken(login);

        assertEquals(token, loginRespostaDTO.token());
        assertEquals(Papel.ADMIN, loginRespostaDTO.papel());
    }

}
