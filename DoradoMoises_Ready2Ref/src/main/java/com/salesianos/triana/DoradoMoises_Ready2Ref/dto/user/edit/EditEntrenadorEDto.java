package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit;

import jakarta.validation.constraints.NotBlank;

public record EditEntrenadorEDto(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        String primerApellido,
        String segundoApellido,

        @NotBlank(message = "El email es obligatorio")
        String email,

        String telefono
) {
}
