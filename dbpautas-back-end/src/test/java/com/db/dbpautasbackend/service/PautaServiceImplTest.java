package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.mapper.PautaMapper;
import com.db.dbpautasbackend.model.dto.PautaEmAndamentoResponse;
import com.db.dbpautasbackend.model.dto.PautaFinalizadaResponse;
import com.db.dbpautasbackend.model.dto.RegistrarPautaRequest;
import com.db.dbpautasbackend.model.enums.Decisao;
import com.db.dbpautasbackend.model.enums.Voto;
import com.db.dbpautasbackend.fixture.PautaFixture;
import com.db.dbpautasbackend.fixture.UsuarioFixture;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.PautaRepository;
import com.db.dbpautasbackend.repository.UsuarioRepository;
import com.db.dbpautasbackend.service.impl.PautaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PautaServiceImplTest {

    @InjectMocks
    private PautaServiceImpl pautaService;
    @Mock
    private PautaRepository pautaRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private VotacaoService votacaoService;
    @Mock
    private Authentication authentication;
    @Mock
    private ValidacaoPautaService validacaoPautaService;


    @Test
    @DisplayName("Dado uma pauta fechada, quando aberta com sucesso, deve retornar a pauta correta.")
    void abrirPautaTest(){
        Pauta pauta = PautaFixture.builderDefault();
        Pauta pautaAbertaEsperada = PautaFixture.builderDePautaAberta();

        when(pautaRepository.findById(anyLong())).thenReturn(Optional.of(pauta));
        when(pautaRepository.save(any())).thenReturn(pautaAbertaEsperada);

        Pauta pautaAbertaObtida = pautaService.abrirPauta(1L, pautaAbertaEsperada.getTempoDeSessaoEmMinutos());

        verify(validacaoPautaService).validaPautaFechada(pauta);
        assertEquals(pautaAbertaEsperada.isAberta(), pautaAbertaObtida.isAberta());
    }

    @Test
    @DisplayName("Dado uma pauta aberta, quando um usuario votar, deve retornar a pauta com voto.")
    void votarPautaTest(){
        Usuario usuario = UsuarioFixture.builderDefault();
        Pauta pautaAberta = PautaFixture.builderDePautaAberta();
        Pauta pautaEsperada = PautaFixture.builderDePautaAbertaComVotos(usuario);

        Voto voto = Voto.SIM;

        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(authentication.getName()).thenReturn("admin");
        when(usuarioRepository.findByCpf("admin")).thenReturn(Optional.of(usuario));
        when(pautaRepository.findById(anyLong())).thenReturn(Optional.of(pautaAberta));
        when(votacaoService.votar(pautaAberta, usuario, voto)).thenReturn(pautaEsperada);

        Pauta pautaObitda = pautaService.votarPauta(1L, voto);

        assertEquals(pautaEsperada.getVotosSim(), pautaObitda.getVotosSim());
    }

    @ParameterizedTest
    @MethodSource("pautas")
    @DisplayName("Dado pautas finalizadas, quando contabilizado os votos, deve retornar a decisão correta.")
    void contabilizarTest(Pauta pauta, Decisao decisaoEsperada){
        Decisao decisaoObtida = pautaService.contabilizar(pauta);
        assertEquals(decisaoEsperada, decisaoObtida);
    }

    public static Stream<Arguments> pautas() {
        return Stream.of(
                Arguments.of(PautaFixture.builderDePautaAprovada(), Decisao.APROVADO),
                Arguments.of(PautaFixture.builderDePautaReprovada(), Decisao.REPROVADO),
                Arguments.of(PautaFixture.builderDePautaEmpatada(), Decisao.EMPATE)
        );
    }

    @Test
    @DisplayName("Dado uma pauta finalizada, quando verificada se ela foi finalizada, deve retornar verdadeiro.")
    void isPautaFinalizadaTest(){
        Pauta pauta = PautaFixture.builderDePautaAprovada();
        boolean isFinalizada = pautaService.isPautaFinalizada(pauta);
        assertEquals(true, isFinalizada);
    }

    @Test
    @DisplayName("Dado uma pauta em andamento, quando verificada se ela foi finalizada, deve retornar falso.")
    void isPautaFinalizadaFalseTest(){
        Pauta pauta = PautaFixture.builderDePautaAberta();
        boolean isFinalizada = pautaService.isPautaFinalizada(pauta);
        assertEquals(false, isFinalizada);
    }

    @Test
    @DisplayName("Dada uma pauta valida, quando salva, deve retornar a pauta salva.")
    void salvarPautaTest(){
        Pauta pauta = PautaFixture.builderDefault();
        RegistrarPautaRequest pautaDTO = PautaMapper.mapPautaToRegistrarPautaDTO(pauta);
        when(pautaRepository.save(any())).thenReturn(pauta);
        Pauta pautaObtida = pautaService.salvar(pautaDTO);
        assertEquals(pauta, pautaObtida);
    }

    @Test
    @DisplayName("Dado pautas fechadas, quando obtidas, deve retornar a lista correta.")
    void obterPautasFechadasTest(){
        Pauta pauta = PautaFixture.builderDefault();
        List<Pauta> pautas = List.of(pauta);
        List<PautaEmAndamentoResponse> pautasEsperadas = PautaMapper.mapListOfPautaToListOfPautaEmAndamentoDTO(pautas);
        when(pautaRepository.findPautasFechadas()).thenReturn(pautas);
        assertIterableEquals(pautaService.obterPautasFechadas(), pautasEsperadas);
    }

    @Test
    @DisplayName("Dado pautas fechadas, quando obtidas por categoria, deve retornar a lista correta.")
    void obterPautasFechadasPorCategoriaTest(){
        Pauta pauta = PautaFixture.builderDefault();
        List<Pauta> pautas = List.of(pauta);
        List<PautaEmAndamentoResponse> pautasEsperadas = PautaMapper.mapListOfPautaToListOfPautaEmAndamentoDTO(pautas);
        when(pautaRepository.findPautasFechadasPorCategoria(any())).thenReturn(pautas);
        assertIterableEquals(pautaService.obterPautasFechadasPorCategoria(pauta.getCategoria()), pautasEsperadas);
    }

    @Test
    @DisplayName("Dado pautas abertas, quando obtidas, deve retornar a lista correta.")
    void obterPautasAbertasTest(){
        Pauta pauta = PautaFixture.builderDePautaAberta();
        List<Pauta> pautas = List.of(pauta);
        List<PautaEmAndamentoResponse> pautasEsperadas = PautaMapper.mapListOfPautaToListOfPautaEmAndamentoDTO(pautas);
        when(pautaRepository.findPautasAbertas()).thenReturn(pautas);
        assertIterableEquals(pautaService.obterPautasAbertas(), pautasEsperadas);
    }

    @Test
    @DisplayName("Dado pautas abertas por categoria, quando obtidas, deve retornar a lista correta.")
    void obterPautasAbertasPorCategoriaTest(){
        Pauta pauta = PautaFixture.builderDePautaAberta();
        List<Pauta> pautas = List.of(pauta);
        List<PautaEmAndamentoResponse> pautasEsperadas = PautaMapper.mapListOfPautaToListOfPautaEmAndamentoDTO(pautas);
        when(pautaRepository.findPautasAbertasPorCategoria(any())).thenReturn(pautas);
        assertIterableEquals(pautaService.obterPautasAbertasPorCategoria(pauta.getCategoria()), pautasEsperadas);
    }

    @Test
    @DisplayName("Dado pautas finalizadas, quando obtidas, deve retornar a lista correta.")
    void buscarPautasFinalizadasTest(){
        Pauta pauta = PautaFixture.builderDePautaAprovada();
        List<Pauta> pautas = List.of(pauta);
        List<PautaFinalizadaResponse> pautasEsperadas = PautaMapper.mapListOfPautaToListOfPautaFinalizadaDTO(pautas, pautaService);
        when(pautaRepository.findPautasAbertas()).thenReturn(pautas);
        assertIterableEquals(pautaService.obterPautasFinalizadas(), pautasEsperadas);
    }

    @Test
    @DisplayName("Dado pautas finalizadas por categoria, quando obtidas, deve retornar a lista correta.")
    void buscarPautasFinalizadasPorCategoriaTest(){
        Pauta pauta = PautaFixture.builderDePautaAprovada();
        List<Pauta> pautas = List.of(pauta);
        List<PautaFinalizadaResponse> pautasEsperadas = PautaMapper.mapListOfPautaToListOfPautaFinalizadaDTO(pautas, pautaService);
        when(pautaRepository.findPautasAbertasPorCategoria(any())).thenReturn(pautas);
        assertIterableEquals(pautaService.obterPautasFinalizadasPorCategoria(pauta.getCategoria()), pautasEsperadas);
    }

}
