package com.db.dbpautasbackend.service;

import com.db.dbpautasbackend.dto.PautaEmAndamentoDTO;
import com.db.dbpautasbackend.dto.PautaFinalizadaDTO;
import com.db.dbpautasbackend.dto.RegistrarPautaDTO;
import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.model.Pauta;

import java.util.List;

public interface PautaService {
    Pauta salvar(RegistrarPautaDTO pautaDTO);
    Pauta abrirPauta(Long id, Integer tempoDeSessaoEmMinutos);
    Pauta votarPauta(Long id, Voto voto);
    List<PautaEmAndamentoDTO> obterPautasFechadas();
    List<PautaEmAndamentoDTO> obterPautasFechadasPorCategoria(Categoria categoria);
    List<PautaEmAndamentoDTO> obterPautasAbertas();
    List<PautaEmAndamentoDTO> obterPautasAbertasPorCategoria(Categoria categoria);
    List<PautaFinalizadaDTO> obterPautasFinalizadas();
    List<PautaFinalizadaDTO> obterPautasFinalizadasPorCategoria(Categoria categoria);

}
