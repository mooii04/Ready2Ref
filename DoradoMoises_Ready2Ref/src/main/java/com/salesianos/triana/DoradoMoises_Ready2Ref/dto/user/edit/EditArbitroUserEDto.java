package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit;

public record EditArbitroUserEDto(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String email,
        String telefono,
        String password,
        int edad,
        String tallaBotas,
        String tallaCamiseta,
        String tallaCalzonas,
        String tallaChandal,
        String foto
) {
}
