package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.UsuarioRepository;
import com.db.dbpautasbackend.service.interfaces.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Override
    public boolean salvar(Usuario usuario) {
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    public Page<Usuario> buscarTodosUsuariosPorPagina(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }
}
