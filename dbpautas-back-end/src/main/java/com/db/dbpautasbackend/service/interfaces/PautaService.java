package com.db.dbpautasbackend.service.interfaces;

import com.db.dbpautasbackend.model.Pauta;

public interface PautaService {
    Pauta salvar(Pauta pauta);
    Pauta abrirPauta(Long id, Integer tempoDeSessaoEmMinutos);
}
