package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.UserRole;

import java.time.LocalDate;
import java.util.Set;

public record EditArbitroDto(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String username,
        String email,
        String telefono,
        String password,
        Set<UserRole> roles,
        LocalDate fechaNacimiento,
        int edad,
        String categoria,
        LocalDate fechaInscripcion,
        int tallaBotas,
        String tallaCamiseta,
        String tallaCalzonas,
        String tallaChandal,
        String foto
) {
}
