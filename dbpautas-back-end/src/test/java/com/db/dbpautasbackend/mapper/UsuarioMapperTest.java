package com.db.dbpautasbackend.mapper;

import com.db.dbpautasbackend.fixture.RegistrarUsuarioRequestFixture;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.model.dto.RegistrarUsuarioRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioMapperTest {

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Teste do método de mapear RegistraUsuarioReques para Usuario")
    void testMapRegistrarUsuarioRequestToUsuario() {
        RegistrarUsuarioRequest registrarUsuarioRequest = RegistrarUsuarioRequestFixture.builderDefault();

        when(passwordEncoder.encode(registrarUsuarioRequest.senha())).thenReturn("senhaCriptografada");

        Usuario usuario = UsuarioMapper.mapRegistrarUsuarioRequestToUsuario(registrarUsuarioRequest, passwordEncoder);

        assertEquals(registrarUsuarioRequest.nome(), usuario.getNome());
        assertEquals(registrarUsuarioRequest.sobrenome(), usuario.getSobrenome());
        assertEquals(registrarUsuarioRequest.email(), usuario.getEmail());
        assertEquals(registrarUsuarioRequest.cpf(), usuario.getCpf());
        assertEquals("senhaCriptografada", usuario.getSenha());
        assertEquals(registrarUsuarioRequest.papel(), usuario.getPapel());
    }
}
