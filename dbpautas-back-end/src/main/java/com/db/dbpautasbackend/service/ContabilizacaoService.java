package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.enums.Decisao;
import com.db.dbpautasbackend.model.Pauta;

public interface ContabilizacaoService {
    Decisao contabilizar(Pauta pauta);
}
