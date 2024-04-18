package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.model.Pauta;

import java.util.List;

public interface PautaService {
    Pauta salvar(Pauta pauta);
    Pauta abrirPauta(Long id, Integer tempoDeSessaoEmMinutos);
    Pauta votarPauta(Long id, Voto voto);
    List<Pauta> obterPautasFechadas();
    List<Pauta> obterPautasFechadasPorCategoria(Categoria categoria);
    List<Pauta> obterPautasAbertas();
    List<Pauta> obterPautasAbertasPorCategoria(Categoria categoria);
    List<Pauta> obterPautasFinalizadas();
    List<Pauta> obterPautasFinalizadasPorCategoria(Categoria categoria);

}
