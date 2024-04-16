package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    Usuario salvar(Usuario usuario);
    Page<Usuario> buscarTodosUsuariosPorPagina(Pageable pageable);
}
