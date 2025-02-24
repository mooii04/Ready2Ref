package com.salesianos.triana.DoradoMoises_Ready2Ref.repository;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
}
