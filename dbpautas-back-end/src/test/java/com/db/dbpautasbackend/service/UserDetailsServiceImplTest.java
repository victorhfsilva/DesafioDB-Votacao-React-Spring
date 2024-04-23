package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.fixture.UserDetailsInfoFixture;
import com.db.dbpautasbackend.info.impl.UserDetailsInfoImpl;
import com.db.dbpautasbackend.repository.UsuarioRepository;
import com.db.dbpautasbackend.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Dado um usuário válido, quando buscado por usuário, deve retornar UserDetails corretamente")
    void loadUserByUsernameTest(){
        UserDetailsInfoImpl userDetailsInfo = UserDetailsInfoFixture.builderDefault();

        when(usuarioRepository.findUserDetailsByCpf(userDetailsInfo.getCpf())).thenReturn(Optional.of(userDetailsInfo));

        UserDetails user = userDetailsService.loadUserByUsername(userDetailsInfo.getCpf());
        String expectedAuthority = userDetailsInfo.getPapel().name();

        assertEquals(userDetailsInfo.getCpf(), user.getUsername());
        assertEquals(userDetailsInfo.getSenha(), user.getPassword());

        assertTrue(user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .anyMatch(authority -> authority.equals(expectedAuthority)));
    }

}
