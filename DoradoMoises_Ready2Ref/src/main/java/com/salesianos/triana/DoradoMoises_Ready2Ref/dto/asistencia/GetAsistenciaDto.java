package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.asistencia;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.entrenamiento.GetEntrenamientoDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.GetArbitroUserEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Asistencia;

import java.util.UUID;

public record GetAsistenciaDto(
        UUID idAsistencia,
        GetEntrenamientoDto entrenamiento
) {

    public static GetAsistenciaDto of(Asistencia a) {
        return new GetAsistenciaDto(
                a.getIdAsistencia(),
                GetEntrenamientoDto.of(a.getEntrenamiento())
        );
    }

}
