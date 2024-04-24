package com.db.dbpautasbackend.service.impl;

import com.db.dbpautasbackend.dto.PautaEmAndamentoDTO;
import com.db.dbpautasbackend.dto.PautaFinalizadaDTO;
import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.enums.Decisao;
import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.mapper.PautaMapper;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.PautaRepository;
import com.db.dbpautasbackend.repository.UsuarioRepository;
import com.db.dbpautasbackend.service.PautaService;
import com.db.dbpautasbackend.service.ValidacaoPautaService;
import com.db.dbpautasbackend.service.VotacaoService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PautaServiceImpl implements PautaService {

    private PautaRepository pautaRepository;
    private UsuarioRepository usuarioRepository;
    private VotacaoService votacaoService;
    private ValidacaoPautaService validacaoPautaService;

    @Override
    public Pauta salvar(RegistrarPautaDTO pautaDTO) {
        Pauta pauta = PautaMapper.mapRegistrarPautaDTOtoPauta(pautaDTO);
        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta abrirPauta(Long id, Integer tempoDeSessaoEmMinutos) {
        Pauta pauta = pautaRepository.findById(id).orElseThrow();
        validacaoPautaService.validaPautaFechada(pauta);

        pauta.setAberta(true);
        pauta.setAbertoAs(LocalDateTime.now());
        pauta.setTempoDeSessaoEmMinutos(tempoDeSessaoEmMinutos != null ? tempoDeSessaoEmMinutos : 1);

        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta votarPauta(Long id, Voto voto) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        String cpf = authentication.getName();
        Usuario usuario = usuarioRepository.findByCpf(cpf).orElseThrow();

        Pauta pauta = pautaRepository.findById(id).orElseThrow();

        return votacaoService.votar(pauta, usuario, voto);
    }

    @Override
    public List<PautaEmAndamentoDTO> obterPautasFechadas() {
        List<Pauta> pautas = pautaRepository.findPautasFechadas();
        return PautaMapper.mapListOfPautaToListOfPautaEmAndamentoDTO(pautas);
    }

    @Override
    public List<PautaEmAndamentoDTO> obterPautasFechadasPorCategoria(Categoria categoria) {
        List<Pauta> pautas = pautaRepository.findPautasFechadasPorCategoria(categoria);
        return PautaMapper.mapListOfPautaToListOfPautaEmAndamentoDTO(pautas);
    }

    @Override
    public List<PautaEmAndamentoDTO> obterPautasAbertas() {
        List<Pauta> pautas = pautaRepository.findPautasAbertas().stream()
                .filter(pauta -> !isPautaFinalizada(pauta))
                .toList();

        return PautaMapper.mapListOfPautaToListOfPautaEmAndamentoDTO(pautas);
    }

    @Override
    public List<PautaEmAndamentoDTO> obterPautasAbertasPorCategoria(Categoria categoria) {
        List<Pauta> pautas = pautaRepository.findPautasAbertasPorCategoria(categoria).stream()
                .filter(pauta -> !isPautaFinalizada(pauta))
                .toList();

        return PautaMapper.mapListOfPautaToListOfPautaEmAndamentoDTO(pautas);
    }

    @Override
    public List<PautaFinalizadaDTO> obterPautasFinalizadas() {
        List<Pauta> pautas = pautaRepository.findPautasAbertas().stream().filter(
                this::isPautaFinalizada
        ).toList();

        return PautaMapper.mapListOfPautaToListOfPautaFinalizadaDTO(pautas, this);
    }

    @Override
    public List<PautaFinalizadaDTO> obterPautasFinalizadasPorCategoria(Categoria categoria) {
        List<Pauta> pautas = pautaRepository.findPautasAbertasPorCategoria(categoria).stream()
                .filter(this::isPautaFinalizada)
                .toList();

        return PautaMapper.mapListOfPautaToListOfPautaFinalizadaDTO(pautas, this);
    }

    public boolean isPautaFinalizada(Pauta pauta) {
        LocalDateTime abertoAs = pauta.getAbertoAs();
        int tempoDeSessaoEmMinutos = pauta.getTempoDeSessaoEmMinutos();

        LocalDateTime agora = LocalDateTime.now();
        long minutosPassados = Duration.between(abertoAs, agora).toMinutes();

        return minutosPassados > tempoDeSessaoEmMinutos;
    }

    public Decisao contabilizar(Pauta pauta) {
        Decisao decisao;
        if(pauta.getVotosSim() > pauta.getVotosNao()) {
            decisao = Decisao.APROVADO;
        } else if (pauta.getVotosSim() == pauta.getVotosNao()) {
            decisao =  Decisao.EMPATE;
        } else {
            decisao = Decisao.REPROVADO;
        }
        return decisao;
    }

}
