package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.entrenamiento;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenamiento;

import java.time.LocalDate;

public record GetEntrenamientoDto(
        LocalDate fecha
) {

    public static GetEntrenamientoDto of(Entrenamiento e) {
        return new GetEntrenamientoDto(
                e.getFecha()
        );
    }

}
