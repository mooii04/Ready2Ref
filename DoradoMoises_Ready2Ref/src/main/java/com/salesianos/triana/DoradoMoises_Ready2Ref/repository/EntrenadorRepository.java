package com.salesianos.triana.DoradoMoises_Ready2Ref.repository;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {

    Optional<Entrenador> findById(UUID userId);

    Entrenador findAllByIdIn(Set<UUID> ids);

}
