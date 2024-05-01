package com.db.dbpautasbackend.service.impl;

import com.db.dbpautasbackend.model.enums.Voto;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.repository.PautaRepository;
import com.db.dbpautasbackend.service.ValidacaoPautaService;
import com.db.dbpautasbackend.service.VotacaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class VotacaoServiceImpl implements VotacaoService {

    private PautaRepository pautaRepository;
    private ValidacaoPautaService validacaoPautaService;

    @Override
    public Pauta votar(Pauta pauta, Usuario usuario, Voto voto) {
        inicializaVotacao(pauta);

        validacaoPautaService.validaPautaAberta(pauta);
        validacaoPautaService.validaPautaFinalizada(pauta);
        validacaoPautaService.validaSePrimeiroVoto(pauta, usuario);

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
