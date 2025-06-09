package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.asistencia;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Asistencia;

import java.util.UUID;

public record GetAsistenciaDto(
        UUID idAsistencia,
        GetArbitroAsistenciaDto arbitro,
        GetEntrenamientoDto entrenamiento
) {

    public static GetAsistenciaDto of(Asistencia asistencia) {
        return new GetAsistenciaDto(asistencia.getIdAsistencia(), GetArbitroAsistenciaDto.of(asistencia.getArbitro()), GetEntrenamientoDto.of(asistencia.getEntrenamiento()));
    }

}
