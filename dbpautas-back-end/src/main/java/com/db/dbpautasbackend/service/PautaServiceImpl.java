package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.PautaRepository;
import com.db.dbpautasbackend.repository.UsuarioRepository;
import com.db.dbpautasbackend.service.interfaces.PautaService;
import com.db.dbpautasbackend.service.interfaces.VotacaoService;
import com.db.dbpautasbackend.validators.PautaValidators;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public Page<Pauta> obterPautasFechadas(Pageable pageable) {
        return pautaRepository.findPautasFechadas(pageable);
    }

    @Override
    public Page<Pauta> obterPautasFechadasPorCategoria(Categoria categoria, Pageable pageable) {
        return pautaRepository.findPautasFechadasPorCategoria(categoria, pageable);
    }

    @Override
    public Page<Pauta> obterPautasAbertas(Pageable pageable) {
        List<Pauta> pautas = pautaRepository.findPautasAbertas(pageable).stream().filter(pauta ->
            !PautaValidators.verificaSessaoFinalizada(pauta)
        ).toList();
        return new PageImpl<>(pautas, pageable, pautas.size());
    }

    @Override
    public Page<Pauta> obterPautasAbertasPorCategoria(Categoria categoria, Pageable pageable) {
        List<Pauta> pautas = pautaRepository.findPautasAbertasPorCategoria(categoria, pageable).stream().filter(pauta ->
                !PautaValidators.verificaSessaoFinalizada(pauta)
        ).toList();
        return new PageImpl<>(pautas, pageable, pautas.size());
    }

    @Override
    public Page<Pauta> obterPautasFinalizadas(Pageable pageable) {
        List<Pauta> pautas = pautaRepository.findPautasAbertas(pageable).stream().filter(
                PautaValidators::verificaSessaoFinalizada
        ).toList();
        return new PageImpl<>(pautas, pageable, pautas.size());
    }

    @Override
    public Page<Pauta> obterPautasFinalizadasPorCategoria(Categoria categoria, Pageable pageable) {
        List<Pauta> pautas = pautaRepository.findPautasAbertasPorCategoria(categoria, pageable).stream().filter(
                PautaValidators::verificaSessaoFinalizada
        ).toList();
        return new PageImpl<>(pautas, pageable, pautas.size());
    }

}
