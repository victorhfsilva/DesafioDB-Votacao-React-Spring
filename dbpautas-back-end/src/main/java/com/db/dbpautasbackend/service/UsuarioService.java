package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.dto.RegistrarUsuarioDTO;
import com.db.dbpautasbackend.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    Usuario salvar(RegistrarUsuarioDTO usuarioDTO);
    Page<Usuario> buscarTodosUsuariosPorPagina(Pageable pageable);
}
