package com.db.dbpautasbackend.repository;

import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.model.Pauta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    @Query("SELECT p FROM Pauta p WHERE p.aberta = false")
    List<Pauta> findPautasFechadas();

    @Query("SELECT p FROM Pauta p WHERE p.aberta = false AND p.categoria = :categoria")
    List<Pauta> findPautasFechadasPorCategoria(Categoria categoria);

    @Query("SELECT p FROM Pauta p WHERE p.aberta = true")
    List<Pauta> findPautasAbertas();

    @Query("SELECT p FROM Pauta p WHERE p.aberta = true AND p.categoria = :categoria")
    List<Pauta> findPautasAbertasPorCategoria(Categoria categoria);
}
