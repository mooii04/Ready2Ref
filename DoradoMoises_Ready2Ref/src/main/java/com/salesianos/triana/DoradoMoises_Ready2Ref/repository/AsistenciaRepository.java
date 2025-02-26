package com.salesianos.triana.DoradoMoises_Ready2Ref.repository;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AsistenciaRepository extends JpaRepository<Asistencia, UUID> {
}
