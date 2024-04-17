package com.db.dbpautasbackend.service.impl;

import com.db.dbpautasbackend.enums.Decisao;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.service.ContabilizacaoService;
import org.springframework.stereotype.Service;

@Service
public class ContabilizacaoServiceImpl implements ContabilizacaoService {
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
