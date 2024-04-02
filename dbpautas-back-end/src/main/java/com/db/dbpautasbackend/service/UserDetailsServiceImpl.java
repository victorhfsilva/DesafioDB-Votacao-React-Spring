package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCpf(username).orElseThrow();
        String senha = usuario.getSenha();
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(usuario.getPapel().name()));
        return new User(username, senha, authorities);
    }
}
