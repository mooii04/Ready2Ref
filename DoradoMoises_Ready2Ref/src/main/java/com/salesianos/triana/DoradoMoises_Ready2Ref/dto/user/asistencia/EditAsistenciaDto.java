package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.asistencia;

import java.util.UUID;

public record EditAsistenciaDto(
        UUID idAsistencia,
        GetArbitroAsistenciaDto arbitro,
        GetEntrenamientoDto entrenamiento
) {
}
