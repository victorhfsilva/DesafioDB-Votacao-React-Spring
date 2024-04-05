package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.fixture.PautaFixture;
import com.db.dbpautasbackend.fixture.RegistrarPautaDTOFixture;
import com.db.dbpautasbackend.mapper.PautaMapper;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.service.interfaces.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PautaController pautaController;

    @MockBean
    private PautaService pautaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Dado uma pauta válida, quando registrada com sucesso, deve retornar verdadeiro")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void registrarTest() throws Exception {
        RegistrarPautaDTO pautaDTO = RegistrarPautaDTOFixture.builderDefault();
        Pauta pauta = PautaMapper.mapRegistrarPautaDTOtoPauta(pautaDTO);
        String pautaDTOJson = objectMapper.writeValueAsString(pautaDTO);

        when(pautaService.salvar(pauta)).thenReturn(pauta);
        mockMvc.perform(MockMvcRequestBuilders.post("/pauta/registrar")
                        .contentType("application/json")
                        .content(pautaDTOJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    @DisplayName("Dado uma pauta fechada, quando aberta com sucesso, deve retornar verdadeiro.")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void abrirPautaTest() throws Exception {
        Pauta pautaAberta = PautaFixture.builderDePautaAberta();
        when(pautaService.abrirPauta(anyLong(), any())).thenReturn(pautaAberta);
        mockMvc.perform(MockMvcRequestBuilders.patch("/pauta/abrir/1")
                        .param("minutos", "180"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

}
