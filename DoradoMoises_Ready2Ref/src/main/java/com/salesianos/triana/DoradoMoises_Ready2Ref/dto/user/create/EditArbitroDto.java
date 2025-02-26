package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create;

import com.salesianos.triana.DoradoMoises_Ready2Ref.validation.FieldsValueMatch;
import com.salesianos.triana.DoradoMoises_Ready2Ref.validation.UniqueUsername;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "verifyPassword",
                message = "Los valores de password y verifyPassword no coinciden")
})

public record EditArbitroDto(

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
        String verifyPassword,
        LocalDate fechaNacimiento,
        int edad,
        String categoria,
        LocalDate fechaInscripcion,

        @Min(30)
        @Max(50)
        Integer tallaBotas,

        String tallaCamiseta,
        String tallaCalzonas,
        String tallaChandal,
        String foto
) {
}
