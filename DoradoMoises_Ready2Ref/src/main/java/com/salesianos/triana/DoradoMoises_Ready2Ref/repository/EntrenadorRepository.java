package com.salesianos.triana.DoradoMoises_Ready2Ref.repository;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntrenadorRepository extends JpaRepository<Entrenador, UUID> {
    // Asegúrate de que NO existe ningún método como:
    // Optional<Entrenador> findByIdEntrenamiento(UUID idEntrenamiento);
    // o cualquier método personalizado que haga referencia a 'entrenamiento'
}
