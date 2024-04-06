package com.db.dbpautasbackend.service.interfaces;

import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.enums.Voto;
import com.db.dbpautasbackend.model.Pauta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PautaService {
    Pauta salvar(Pauta pauta);
    Pauta abrirPauta(Long id, Integer tempoDeSessaoEmMinutos);
    Pauta votarPauta(Long id, Voto voto);
    Page<Pauta> obterPautasFechadas(Pageable pageable);
    Page<Pauta> obterPautasFechadasPorCategoria(Categoria categoria, Pageable pageable);
    Page<Pauta> obterPautasAbertas(Pageable pageable);
    Page<Pauta> obterPautasAbertasPorCategoria(Categoria categoria, Pageable pageable);
    Page<Pauta> obterPautasFinalizadas(Pageable pageable);
    Page<Pauta> obterPautasFinalizadasPorCategoria(Categoria categoria, Pageable pageable);

}
