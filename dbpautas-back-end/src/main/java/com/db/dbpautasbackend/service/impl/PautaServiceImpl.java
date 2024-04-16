package com.db.dbpautasbackend.service.impl;

import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.PautaRepository;
import com.db.dbpautasbackend.repository.UsuarioRepository;
import com.db.dbpautasbackend.service.PautaService;
import com.db.dbpautasbackend.service.VotacaoService;
import com.db.dbpautasbackend.validators.PautaValidators;
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

    @Override
    public Pauta salvar(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta abrirPauta(Long id, Integer tempoDeSessaoEmMinutos) {
        Pauta pauta = pautaRepository.findById(id).orElseThrow();
        PautaValidators.validaPautaFechada(pauta);
        pauta.setAberta(true);
        pauta.setAbertoAs(LocalDateTime.now());
        pauta.setTempoDeSessaoEmMinutos(tempoDeSessaoEmMinutos != null ? tempoDeSessaoEmMinutos : 1);
        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta votarPauta(Long id, Voto voto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String cpf = authentication.getName();
        Usuario usuario = usuarioRepository.findByCpf(cpf).orElseThrow();
        Pauta pauta = pautaRepository.findById(id).orElseThrow();
        return votacaoService.votar(pauta, usuario, voto);
    }

    @Override
    public List<Pauta> obterPautasFechadas() {
        return pautaRepository.findPautasFechadas();
    }

    @Override
    public List<Pauta> obterPautasFechadasPorCategoria(Categoria categoria) {
        return pautaRepository.findPautasFechadasPorCategoria(categoria);
    }

    @Override
    public List<Pauta> obterPautasAbertas() {
        return pautaRepository.findPautasAbertas().stream().filter(pauta ->
            !isPautaFinalizada(pauta)
        ).toList();
    }

    @Override
    public List<Pauta> obterPautasAbertasPorCategoria(Categoria categoria) {
        return pautaRepository.findPautasAbertasPorCategoria(categoria).stream().filter(pauta ->
                !isPautaFinalizada(pauta)
        ).toList();
    }

    @Override
    public List<Pauta> obterPautasFinalizadas() {
        return pautaRepository.findPautasAbertas().stream().filter(
                this::isPautaFinalizada
        ).toList();
    }

    @Override
    public List<Pauta> obterPautasFinalizadasPorCategoria(Categoria categoria) {
        return pautaRepository.findPautasAbertasPorCategoria(categoria).stream().filter(
                this::isPautaFinalizada
        ).toList();
    }

    public boolean isPautaFinalizada(Pauta pauta) {
        LocalDateTime abertoAs = pauta.getAbertoAs();
        int tempoDeSessaoEmMinutos = pauta.getTempoDeSessaoEmMinutos();
        LocalDateTime agora = LocalDateTime.now();
        long minutosPassados = Duration.between(abertoAs, agora).toMinutes();
        return minutosPassados > tempoDeSessaoEmMinutos;
    }

}
