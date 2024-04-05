package com.db.dbpautasbackend.service.interfaces;

import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.model.Pauta;

public interface PautaService {
    Pauta salvar(Pauta pauta);
    Pauta abrirPauta(Long id, Integer tempoDeSessaoEmMinutos);

    boolean votarPauta(Long id, Voto voto);
}
