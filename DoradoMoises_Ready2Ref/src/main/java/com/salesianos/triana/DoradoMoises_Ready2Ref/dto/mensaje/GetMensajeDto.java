package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Mensaje;

import java.time.LocalDate;

public record GetMensajeDto(
        String asunto,
        String contenido,
        LocalDate fechaEnvio
) {

    public static GetMensajeDto of(Mensaje m) {
        return new GetMensajeDto(
                m.getAsunto(),
                m.getContenido(),
                m.getFechaEnvio()
        );
    }

}
