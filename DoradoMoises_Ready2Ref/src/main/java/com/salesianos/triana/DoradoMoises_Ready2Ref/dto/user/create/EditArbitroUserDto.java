package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create;

public record EditArbitroUserDto(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String email,
        String telefono,
        String username,
        String password,
        int tallaBotas,
        String tallaCamiseta,
        String tallaCalzonas,
        String tallaChandal,
        String foto
) {
}
