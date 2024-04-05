package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.exception.PautaFechadaException;
import com.db.dbpautasbackend.exception.SessaoFinalizadaException;
import com.db.dbpautasbackend.exception.VotoInvalidoException;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.PautaRepository;
import com.db.dbpautasbackend.service.interfaces.VotacaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class VotacaoServiceImpl implements VotacaoService {

    private PautaRepository pautaRepository;

    @Override
    public Pauta votar(Pauta pauta, Usuario usuario, Voto voto) {
        inicializaVotacao(pauta);
        validaPautaAberta(pauta);
        validaSessaoFinalizada(pauta);
        validaSePrimeiroVoto(pauta, usuario);
        return votacao(pauta, usuario, voto);
    }

    private Pauta votacao(Pauta pauta, Usuario usuario, Voto voto) {
        pauta.getEleitores().add(usuario);
        int votosSim = pauta.getVotosSim();
        int votosNao = pauta.getVotosNao();
        if (voto.isSim()) {
            pauta.setVotosSim(votosSim+1);
        } else {
            pauta.setVotosNao(votosNao+1);
        }
        return pautaRepository.save(pauta);
    }

    private static void validaSessaoFinalizada(Pauta pauta) {
        LocalDateTime abertoAs = pauta.getAbertoAs();
        int tempoDeSessaoEmMinutos = pauta.getTempoDeSessaoEmMinutos();
        LocalDateTime agora = LocalDateTime.now();
        long minutosPassados = Duration.between(abertoAs, agora).toMinutes();
        if (minutosPassados > tempoDeSessaoEmMinutos) {
            throw new SessaoFinalizadaException("O tempo de sessão já expirou. Sessão finalizada.");
        }
    }

    private static void validaPautaAberta(Pauta pauta){
        if (!pauta.isAberta()){
            throw new PautaFechadaException("Esta pauta ainda não foi aberta a votação.");
        }
    }

    private static void validaSePrimeiroVoto(Pauta pauta, Usuario usuario){
        if (pauta.getEleitores().contains(usuario)){
            throw new VotoInvalidoException("Não é permitido votar duas vezes na mesma pauta.");
        }
    }

    private static void inicializaVotacao(Pauta pauta) {
        if (pauta.getEleitores() == null){
            pauta.setEleitores(List.of());
        }
    }
}
