package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Mensaje;
import java.time.LocalDate;
import java.util.UUID;

public record GetMensajeListadoDto(
        UUID id,
        String asunto,
        String contenido,
        LocalDate fechaEnvio,
        boolean leido
) {
    public static GetMensajeListadoDto of(Mensaje m) {
        return new GetMensajeListadoDto(
                m.getId(),
                m.getAsunto(),
                m.getContenido(),
                m.getFechaEnvio(),
                m.isLeido()
        );
    }
}
