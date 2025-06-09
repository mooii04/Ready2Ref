package com.salesianos.triana.DoradoMoises_Ready2Ref.repository;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Asistencia;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AsistenciaRepository extends JpaRepository<Asistencia, UUID> {

    List<Asistencia> findByUsuario(Arbitro arbitro);

}
