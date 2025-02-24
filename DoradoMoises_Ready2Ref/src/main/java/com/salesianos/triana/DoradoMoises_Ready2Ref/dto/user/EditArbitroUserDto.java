package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.UserRole;

import java.time.LocalDate;
import java.util.Set;

public record EditArbitroUserDto(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String email,
        String telefono,
        String password,
        int edad,
        int tallaBotas,
        String tallaCamiseta,
        String tallaCalzonas,
        String tallaChandal,
        String foto
) {
}
