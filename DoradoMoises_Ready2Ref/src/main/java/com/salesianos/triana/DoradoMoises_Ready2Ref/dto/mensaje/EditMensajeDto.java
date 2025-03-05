package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record EditMensajeDto(
        @NotBlank(message = "El asunto no puede estar vacío")
        String asunto,

        @NotBlank(message = "El contenido no puede estar vacío")
        String contenido,

        LocalDate fechaEnvio,
        boolean leido
) {

}
