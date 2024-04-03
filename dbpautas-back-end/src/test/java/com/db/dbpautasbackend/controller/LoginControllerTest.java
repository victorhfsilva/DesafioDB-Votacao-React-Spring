package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.dto.LoginDTO;
import com.db.dbpautasbackend.fixture.LoginDTOFixture;
import com.db.dbpautasbackend.service.LoginServiceImpl;
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
        LoginDTO login = LoginDTOFixture.buiderDefault();
        String loginJson = objectMapper.writeValueAsString(login);

        when(loginService.gerarToken(login)).thenReturn("token");
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                                                .contentType("application/json")
                                                .content(loginJson))
                                                .andExpect(MockMvcResultMatchers.status().isOk())
                                                .andExpect(MockMvcResultMatchers.content().string("token"));
    }
}
