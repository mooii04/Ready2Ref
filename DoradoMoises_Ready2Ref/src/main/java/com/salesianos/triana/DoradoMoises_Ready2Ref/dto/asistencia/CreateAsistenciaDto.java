package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.asistencia;

import java.util.UUID;

public record CreateAsistenciaDto(
    Long entrenamientoId,
    UUID arbitroId,
    boolean asistio
) {

    public static CreateAsistenciaDto of(Long entrenamientoId, UUID arbitroId, boolean asistio) {
        return new CreateAsistenciaDto(entrenamientoId, arbitroId, asistio);
    }

}
