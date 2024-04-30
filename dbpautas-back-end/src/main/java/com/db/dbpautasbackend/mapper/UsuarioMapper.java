package com.db.dbpautasbackend.mapper;

import com.db.dbpautasbackend.model.dto.RegistrarUsuarioRequest;
import com.db.dbpautasbackend.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UsuarioMapper {
    static Usuario mapRegistrarUsuarioRequestToUsuario(RegistrarUsuarioRequest usuarioDTO, PasswordEncoder passwordEncoder){
        return Usuario.builder()
                .nome(usuarioDTO.nome())
                .sobrenome(usuarioDTO.sobrenome())
                .email(usuarioDTO.email())
                .cpf(usuarioDTO.cpf())
                .senha(passwordEncoder.encode(usuarioDTO.senha()))
                .papel(usuarioDTO.papel())
                .build();
    }
}
