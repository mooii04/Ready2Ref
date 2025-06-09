package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.asistencia;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Asistencia;

import java.time.LocalDate;
import java.util.UUID;

public record GetAsistenciaDto(
    UUID id,
    LocalDate fechaEntrenamiento,
    UUID arbitroId,
    String arbitroNombre,
    boolean asistio
) {

    public static GetAsistenciaDto of(Asistencia asistencia) {
        return new GetAsistenciaDto(
            asistencia.getIdAsistencia(),
            asistencia.getEntrenamiento().getFecha(),
            asistencia.getArbitro().getId(),
            asistencia.getArbitro().getNombre(),
            asistencia.isAsistio()
        );
    }

}
