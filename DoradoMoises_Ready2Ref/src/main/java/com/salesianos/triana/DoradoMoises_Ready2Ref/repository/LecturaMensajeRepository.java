package com.salesianos.triana.DoradoMoises_Ready2Ref.repository;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.LecturaMensaje;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Mensaje;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LecturaMensajeRepository extends JpaRepository<LecturaMensaje, UUID> {
    Optional<LecturaMensaje> findByUsuarioAndMensaje(User usuario, Mensaje mensaje);
}
