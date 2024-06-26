package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;

public interface ValidacaoPautaService {
    void validaPautaFinalizada(Pauta pauta);
    void validaPautaAberta(Pauta pauta);
    void validaPautaFechada(Pauta pauta);
    void validaSePrimeiroVoto(Pauta pauta, Usuario usuario);
}
