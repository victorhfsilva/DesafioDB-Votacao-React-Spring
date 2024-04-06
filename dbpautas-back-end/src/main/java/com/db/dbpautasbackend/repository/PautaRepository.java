package com.db.dbpautasbackend.repository;

import com.db.dbpautasbackend.enums.Categoria;
import com.db.dbpautasbackend.model.Pauta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    @Query("SELECT p FROM Pauta p WHERE p.aberta = false")
    Page<Pauta> findPautasFechadas(Pageable pageable);

    @Query("SELECT p FROM Pauta p WHERE p.aberta = false AND p.categoria = :categoria")
    Page<Pauta> findPautasFechadasPorCategoria(Categoria categoria, Pageable pageable);

    @Query("SELECT p FROM Pauta p WHERE p.aberta = true")
    Page<Pauta> findPautasAbertas(Pageable pageable);

    @Query("SELECT p FROM Pauta p WHERE p.aberta = true AND p.categoria = :categoria")
    Page<Pauta> findPautasAbertasPorCategoria(Categoria categoria, Pageable pageable);
}
