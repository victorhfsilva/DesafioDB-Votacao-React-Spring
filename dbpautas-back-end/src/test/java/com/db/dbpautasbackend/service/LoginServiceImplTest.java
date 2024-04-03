package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.dto.LoginDTO;
import com.db.dbpautasbackend.fixture.LoginDTOFixture;
import com.db.dbpautasbackend.service.interfaces.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
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
        LoginDTO login = LoginDTOFixture.buiderDefault();
        when(tokenService.gerarToken(login.cpf())).thenReturn("token");
        assertEquals("token", loginService.gerarToken(login));
    }

}
