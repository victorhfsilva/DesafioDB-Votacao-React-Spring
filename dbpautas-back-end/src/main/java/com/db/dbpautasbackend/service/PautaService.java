package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.model.dto.PautaEmAndamentoResponse;
import com.db.dbpautasbackend.model.dto.PautaFinalizadaResponse;
import com.db.dbpautasbackend.model.dto.RegistrarPautaRequest;
import com.db.dbpautasbackend.model.enums.Categoria;
import com.db.dbpautasbackend.model.enums.Decisao;
import com.db.dbpautasbackend.model.enums.Voto;
import com.db.dbpautasbackend.model.Pauta;

import java.util.List;

public interface PautaService {
    Pauta salvar(RegistrarPautaRequest pautaDTO);
    Pauta abrirPauta(Long id, Integer tempoDeSessaoEmMinutos);
    Pauta votarPauta(Long id, Voto voto);
    List<PautaEmAndamentoResponse> obterPautasFechadas();
    List<PautaEmAndamentoResponse> obterPautasFechadasPorCategoria(Categoria categoria);
    List<PautaEmAndamentoResponse> obterPautasAbertas();
    List<PautaEmAndamentoResponse> obterPautasAbertasPorCategoria(Categoria categoria);
    List<PautaFinalizadaResponse> obterPautasFinalizadas();
    List<PautaFinalizadaResponse> obterPautasFinalizadasPorCategoria(Categoria categoria);

    Decisao contabilizar(Pauta pauta);
}
