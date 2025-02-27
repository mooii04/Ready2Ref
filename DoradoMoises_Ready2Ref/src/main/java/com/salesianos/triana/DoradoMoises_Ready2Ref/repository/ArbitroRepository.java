package com.salesianos.triana.DoradoMoises_Ready2Ref.repository;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ArbitroRepository extends JpaRepository<Arbitro, Long>, JpaSpecificationExecutor<Arbitro> {

    Optional<Arbitro> findById(UUID id);

    Optional<Arbitro> findByUsername(String username);

    @Query("SELECT a FROM Arbitro a WHERE (:search IS NULL OR a.nombre LIKE %:search%)")
    Page<Arbitro> buscarArbitrosConFiltros(
            @Param("search") String search,
            Pageable pageable);
}
