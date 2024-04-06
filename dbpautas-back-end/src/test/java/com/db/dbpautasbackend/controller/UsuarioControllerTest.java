package com.db.dbpautasbackend.controller;

import com.db.dbpautasbackend.dto.RegistrarUsuarioDTO;
import com.db.dbpautasbackend.fixture.RegistrarUsuarioDTOFixture;
import com.db.dbpautasbackend.mapper.UsuarioMapper;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.service.interfaces.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UsuarioController usuarioController;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Dado uma usuário válido, quando registrado com sucesso, deve retornar verdadeiro")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void registrarTest() throws Exception {
        RegistrarUsuarioDTO usuarioDTO = RegistrarUsuarioDTOFixture.builderDefault();
        Usuario usuario = UsuarioMapper.mapRegistrarUsuarioDTOToUsuario(usuarioDTO, passwordEncoder);
        String usuarioDTOJson = objectMapper.writeValueAsString(usuarioDTO);

        when(passwordEncoder.encode(usuarioDTO.senha())).thenReturn(usuarioDTO.senha());
        when(usuarioService.salvar(usuario)).thenReturn(usuario);
        mockMvc.perform(MockMvcRequestBuilders.post("/usuario/registrar")
                        .contentType("application/json")
                        .content(usuarioDTOJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    @DisplayName("Dado uma usuário com senha fraca, quando registrado com sucesso, deve retornar bad request")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void registrarComSenhaFracaTest() throws Exception {
        RegistrarUsuarioDTO usuarioDTO = RegistrarUsuarioDTOFixture.builderComSenhaFraca();
        Usuario usuario = UsuarioMapper.mapRegistrarUsuarioDTOToUsuario(usuarioDTO, passwordEncoder);
        String usuarioDTOJson = objectMapper.writeValueAsString(usuarioDTO);

        when(passwordEncoder.encode(usuarioDTO.senha())).thenReturn(usuarioDTO.senha());
        when(usuarioService.salvar(usuario)).thenReturn(usuario);
        mockMvc.perform(MockMvcRequestBuilders.post("/usuario/registrar")
                        .contentType("application/json")
                        .content(usuarioDTOJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Dado uma usuário com cpf invalido, quando registrado com sucesso, deve retornar bad request")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void registrarComCpfInvalidoTest() throws Exception {
        RegistrarUsuarioDTO usuarioDTO = RegistrarUsuarioDTOFixture.builderComCpfInvalido();
        Usuario usuario = UsuarioMapper.mapRegistrarUsuarioDTOToUsuario(usuarioDTO, passwordEncoder);
        String usuarioDTOJson = objectMapper.writeValueAsString(usuarioDTO);

        when(passwordEncoder.encode(usuarioDTO.senha())).thenReturn(usuarioDTO.senha());
        when(usuarioService.salvar(usuario)).thenReturn(usuario);
        mockMvc.perform(MockMvcRequestBuilders.post("/usuario/registrar")
                        .contentType("application/json")
                        .content(usuarioDTOJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
