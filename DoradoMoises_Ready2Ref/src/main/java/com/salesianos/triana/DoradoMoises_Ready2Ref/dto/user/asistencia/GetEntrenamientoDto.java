package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.asistencia;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenamiento;

import java.time.LocalDate;
import java.util.UUID;

public record GetEntrenamientoDto(
        UUID idEntrenamiento,
        LocalDate fecha
) {

    public static GetEntrenamientoDto of(Entrenamiento entrenamiento) {
        return new GetEntrenamientoDto(entrenamiento.getIdEntrenamiento(), entrenamiento.getFecha());
    }

}
