package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.UsuarioRepository;
import com.db.dbpautasbackend.service.interfaces.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Page<Usuario> buscarTodosUsuariosPorPagina(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }
}
