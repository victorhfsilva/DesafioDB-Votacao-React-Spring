package com.db.dbpautasbackend.service.interfaces;

import com.db.dbpautasbackend.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    boolean salvar(Usuario usuario);
    Page<Usuario> buscarTodosUsuariosPorPagina(Pageable pageable);
}
