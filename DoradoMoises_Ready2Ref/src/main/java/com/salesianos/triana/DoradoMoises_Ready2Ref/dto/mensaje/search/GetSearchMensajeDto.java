package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.search;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Mensaje;

public record GetSearchMensajeDto(
        String asunto,
        String contenido,
        String fechaEnvio
) {

    public static GetSearchMensajeDto of(Mensaje m) {
        return new GetSearchMensajeDto(
                m.getAsunto(),
                m.getContenido(),
                m.getFechaEnvio()
        );
    }

}
