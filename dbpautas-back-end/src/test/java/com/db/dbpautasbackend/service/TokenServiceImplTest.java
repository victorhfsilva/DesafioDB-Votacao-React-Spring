package com.db.dbpautasbackend.service;


import com.db.dbpautasbackend.fixture.TokenFixture;
import com.db.dbpautasbackend.fixture.UserDetailsInfoFixture;
import com.db.dbpautasbackend.info.UserDetailsInfoImpl;
import com.db.dbpautasbackend.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Mock
    private UsuarioRepository usuarioRepository;


    @ParameterizedTest
    @MethodSource("tokens")
    @DisplayName("Dado um token, quando verificado, deve retornar se é válido ou não corretamente.")
    void isTokenValidoTest(String token, boolean resultadoEsperado){
        UserDetailsInfoImpl userDetailsInfo = UserDetailsInfoFixture.builderDefault();
        when(usuarioRepository.findUserDetailsByCpf(userDetailsInfo.getCpf())).thenReturn(Optional.of(userDetailsInfo));

        boolean resultado = tokenService.isTokenValido(token);
        assertEquals(resultadoEsperado, resultado);
    }

    private static Stream<Arguments> tokens() {
        return Stream.of(
            Arguments.of(TokenFixture.builderDefault(), true)
        );
    }
}
