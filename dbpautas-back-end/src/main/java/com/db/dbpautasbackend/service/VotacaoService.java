package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.model.enums.Voto;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;

public interface VotacaoService {
    Pauta votar(Pauta pauta, Usuario usuario, Voto voto);
}
