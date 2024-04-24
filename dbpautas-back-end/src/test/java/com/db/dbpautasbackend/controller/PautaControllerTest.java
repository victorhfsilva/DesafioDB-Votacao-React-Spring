package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.dto.PautaEmAndamentoDTO;
import com.db.dbpautasbackend.dto.PautaFinalizadaDTO;
import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.fixture.*;
import com.db.dbpautasbackend.mapper.PautaMapper;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
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

        when(pautaService.salvar(pautaDTO)).thenReturn(pauta);

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

    @Test
    @DisplayName("Dado uma pauta aberta, quando o usuário votar com sucesso, deve retornar verdadeiro.")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void votarPautaTest() throws Exception {
        Usuario usuario = UsuarioFixture.builderDefault();
        Pauta pauta = PautaFixture.builderDePautaAbertaComVotos(usuario);

        when(pautaService.votarPauta(anyLong(), eq(Voto.SIM))).thenReturn(pauta);

        mockMvc.perform(MockMvcRequestBuilders.patch("/pauta/votar/1")
                        .param("voto", "SIM"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    @DisplayName("Dado uma lista de pautas, quando o usuário buscar por pautas fechadas, deve retornar as pautas corretamente.")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void buscarPautasFechadaTest() throws Exception {
        PautaEmAndamentoDTO pautaEsperada = PautaEmAndamentoDTOFixture.builderDefault();
        List<PautaEmAndamentoDTO> pautasDTOs = List.of(pautaEsperada);

        when(pautaService.obterPautasFechadas()).thenReturn(pautasDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get("/pauta/fechada/")
                        .param("pagina", "0")
                        .param("tamanho", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Título da Pauta"));
    }

    @Test
    @DisplayName("Dado uma lista de pautas, quando o usuário buscar por pautas fechadas filtradas por categoria, deve retornar as pautas corretamente.")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void buscarPautasFechadaPorCategoriaTest() throws Exception {
        PautaEmAndamentoDTO pautaEsperada = PautaEmAndamentoDTOFixture.builderDefault();
        List<PautaEmAndamentoDTO> pautasDTOs = List.of(pautaEsperada);

        when(pautaService.obterPautasFechadasPorCategoria(Categoria.EDUCACAO)).thenReturn(pautasDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get("/pauta/fechada/EDUCACAO")
                        .param("pagina", "0")
                        .param("tamanho", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Título da Pauta"));
    }

    @Test
    @DisplayName("Dado uma lista de pautas, quando o usuário buscar por pautas abertas, deve retornar as pautas corretamente.")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void buscarPautasAbertaTest() throws Exception {
        PautaEmAndamentoDTO pautaEsperada = PautaEmAndamentoDTOFixture.builderDefault();
        List<PautaEmAndamentoDTO> pautasDTOs = List.of(pautaEsperada);

        when(pautaService.obterPautasAbertas()).thenReturn(pautasDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get("/pauta/aberta/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Título da Pauta"));
    }

    @Test
    @DisplayName("Dado uma lista de pautas, quando o usuário buscar por pautas abertas filtradas por categoria, deve retornar as pautas corretamente.")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void buscarPautasAbertaPorCategoriaTest() throws Exception {
        PautaEmAndamentoDTO pautaEsperada = PautaEmAndamentoDTOFixture.builderDefault();
        List<PautaEmAndamentoDTO> pautasDTOs = List.of(pautaEsperada);

        when(pautaService.obterPautasAbertasPorCategoria(Categoria.EDUCACAO)).thenReturn(pautasDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get("/pauta/aberta/EDUCACAO")
                        .param("pagina", "0")
                        .param("tamanho", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Título da Pauta"));
    }

    @Test
    @DisplayName("Dado uma lista de pautas, quando o usuário buscar por pautas finalizadas, deve retornar as pautas corretamente.")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void buscarPautasFinalizadasTest() throws Exception {
        PautaFinalizadaDTO pautaEsperada = PautaFinalizadaDTOFixture.builderDefault();
        List<PautaFinalizadaDTO> pautasDTOs = List.of(pautaEsperada);

        when(pautaService.obterPautasFinalizadas()).thenReturn(pautasDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get("/pauta/finalizada/")
                        .param("pagina", "0")
                        .param("tamanho", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Título da Pauta"));
    }

    @Test
    @DisplayName("Dado uma lista de pautas, quando o usuário buscar por pautas finalizadas filtradas por categoria, deve retornar as pautas corretamente.")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void buscarPautasFinalizadasPorCategoriaTest() throws Exception {
        PautaFinalizadaDTO pautaEsperada = PautaFinalizadaDTOFixture.builderDefault();
        List<PautaFinalizadaDTO> pautasDTOs = List.of(pautaEsperada);

        when(pautaService.obterPautasFinalizadas()).thenReturn(pautasDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get("/pauta/finalizada/EDUCACAO")
                        .param("pagina", "0")
                        .param("tamanho", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Título da Pauta"));
    }

}
