package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.pack.GetDtoPack;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EditArbitroAdminEDto(

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        String primerApellido,
        String segundoApellido,

        @NotBlank(message = "El email es obligatorio")
        String email,

        String telefono,
        String categoria,

        @Min(30)
        @Max(50)
        Integer tallaBotas,

        String tallaCamiseta,
        String tallaCalzonas,
        String tallaChandal,
        String foto,
        GetDtoPack pack
) {
}
