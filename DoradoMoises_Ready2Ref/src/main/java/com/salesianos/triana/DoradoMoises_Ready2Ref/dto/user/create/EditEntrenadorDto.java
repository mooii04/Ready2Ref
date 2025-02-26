package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create;

import com.salesianos.triana.DoradoMoises_Ready2Ref.validation.FieldsValueMatch;
import com.salesianos.triana.DoradoMoises_Ready2Ref.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "verifyPassword",
                message = "Los valores de password y verifyPassword no coinciden")
})

public record EditEntrenadorDto (

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        String primerApellido,
        String segundoApellido,

        @UniqueUsername
        String username,

        @NotBlank(message = "El email es obligatorio")
        String email,

        String telefono,
        String password,
        String verifyPassword
){
}
