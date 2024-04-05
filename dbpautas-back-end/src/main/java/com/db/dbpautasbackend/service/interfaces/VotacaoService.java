package com.db.dbpautasbackend.service.interfaces;

import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.model.Pauta;
import com.db.dbpautasbackend.model.Usuario;

public interface VotacaoService {
    Pauta votar(Pauta pauta, Usuario usuario, Voto voto);
}
