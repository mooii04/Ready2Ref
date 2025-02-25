package com.salesianos.triana.DoradoMoises_Ready2Ref.repository;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ArbitroRepository extends JpaRepository<Arbitro, Long> {

    Optional<Arbitro> findById(UUID id);
}
