package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.exception.PautaFechadaException;
import com.db.dbpautasbackend.exception.SessaoFinalizadaException;
import com.db.dbpautasbackend.exception.VotoInvalidoException;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.PautaRepository;
import com.db.dbpautasbackend.service.interfaces.VotacaoService;
import com.db.dbpautasbackend.validators.PautaValidators;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VotacaoServiceImpl implements VotacaoService {

    private PautaRepository pautaRepository;

    @Override
    public Pauta votar(Pauta pauta, Usuario usuario, Voto voto) {
        inicializaVotacao(pauta);
        PautaValidators.validaPautaAberta(pauta);
        PautaValidators.validaSessaoFinalizada(pauta);
        PautaValidators.validaSePrimeiroVoto(pauta, usuario);
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


    private static void inicializaVotacao(Pauta pauta) {
        if (pauta.getEleitores() == null){
            pauta.setEleitores(new ArrayList<>());
        }
    }
}
