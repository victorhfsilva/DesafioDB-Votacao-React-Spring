package com.db.dbpautasbackend.service.impl;

import com.db.dbpautasbackend.model.dto.RegistrarUsuarioRequest;
import com.db.dbpautasbackend.mapper.UsuarioMapper;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.UsuarioRepository;
import com.db.dbpautasbackend.service.UsuarioService;

import com.db.dbpautasbackend.service.ValidacaoUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private ValidacaoUsuarioService validacaoUsuarioService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario salvar(RegistrarUsuarioRequest usuarioDTO) {
        validacaoUsuarioService.validarSituacaoRegularDoCpf(usuarioDTO.cpf());
        Usuario usuario = UsuarioMapper.mapRegistrarUsuarioRequestToUsuario(usuarioDTO, passwordEncoder);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Page<Usuario> buscarTodosUsuariosPorPagina(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }
}
