package com.db.dbpautasbackend.service.impl;

import com.db.dbpautasbackend.repository.UsuarioRepository;
import com.db.dbpautasbackend.model.info.UserDetailsInfo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetailsInfo userDetailsInfo = usuarioRepository.findUserDetailsByCpf(username).orElseThrow();

        String senha = userDetailsInfo.getSenha();
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userDetailsInfo.getPapel().name()));

        return new User(username, senha, authorities);
    }
}
