package com.db.dbpautasbackend.service.impl;

import com.db.dbpautasbackend.exception.PautaAbertaException;
import com.db.dbpautasbackend.exception.PautaFechadaException;
import com.db.dbpautasbackend.exception.SessaoFinalizadaException;
import com.db.dbpautasbackend.exception.VotoInvalidoException;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;
import com.db.dbpautasbackend.service.ValidacaoPautaService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ValidacaoPautaServiceImpl implements ValidacaoPautaService {

    public void validaPautaFinalizada(Pauta pauta) {
        LocalDateTime abertoAs = pauta.getAbertoAs();
        int tempoDeSessaoEmMinutos = pauta.getTempoDeSessaoEmMinutos();
        LocalDateTime agora = LocalDateTime.now();
        long minutosPassados = Duration.between(abertoAs, agora).toMinutes();
        if (minutosPassados > tempoDeSessaoEmMinutos) {
            throw new SessaoFinalizadaException("O tempo de sessão já expirou. Sessão finalizada.");
        }
    }

    public void validaPautaAberta(Pauta pauta){
        if (!pauta.isAberta()){
            throw new PautaFechadaException("Esta pauta ainda não foi aberta a votação.");
        }
    }

    public void validaPautaFechada(Pauta pauta){
        if (pauta.isAberta()){
            throw new PautaAbertaException("Esta pauta já foi aberta para votação.");
        }
    }

    public void validaSePrimeiroVoto(Pauta pauta, Usuario usuario){
        if (pauta.getEleitores() == null){
            return;
        }
        if (pauta.getEleitores().contains(usuario)){
            throw new VotoInvalidoException("Não é permitido votar duas vezes na mesma pauta.");
        }
    }

}
