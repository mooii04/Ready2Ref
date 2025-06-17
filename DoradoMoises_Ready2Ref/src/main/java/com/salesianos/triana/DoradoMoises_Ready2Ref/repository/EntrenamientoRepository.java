package com.salesianos.triana.DoradoMoises_Ready2Ref.repository;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {

    Optional<Entrenamiento> findById(UUID id);

}
