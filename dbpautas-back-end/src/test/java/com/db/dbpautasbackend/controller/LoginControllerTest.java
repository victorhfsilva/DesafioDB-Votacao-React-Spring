package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.model.dto.LoginRequest;
import com.db.dbpautasbackend.model.dto.LoginResponse;
import com.db.dbpautasbackend.fixture.LoginRequestFixture;
import com.db.dbpautasbackend.fixture.LoginResponseFixture;
import com.db.dbpautasbackend.service.impl.LoginServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private LoginController loginController;

    @MockBean
    private LoginServiceImpl loginService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Dado um login válido, quando logado, deve gerar um token corretamente")
    void loginTest() throws Exception {
        LoginRequest login = LoginRequestFixture.buiderDefault();
        LoginResponse loginResposta = LoginResponseFixture.buiderDefault();

        String loginJson = objectMapper.writeValueAsString(login);

        when(loginService.gerarToken(login)).thenReturn(loginResposta);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                                                .contentType("application/json")
                                                .content(loginJson))
                                                .andExpect(MockMvcResultMatchers.status().isOk())
                                                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("token"))
                                                .andExpect(MockMvcResultMatchers.jsonPath("$.papel").value("ADMIN"));
    }
}
