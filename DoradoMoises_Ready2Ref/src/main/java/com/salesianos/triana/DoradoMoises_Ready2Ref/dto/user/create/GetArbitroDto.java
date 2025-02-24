package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;

import java.time.LocalDate;

public record GetArbitroDto(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String username,
        String email,
        String telefono,
        String password,
        String roles,
        LocalDate fechaNacimiento,
        int edad,
        String categoria,
        LocalDate fechaInscripcion,
        int tallaBotas,
        String tallaCamiseta,
        String tallaCalzonas,
        String tallaChandal,
        String foto
){

    public static GetArbitroDto of(Arbitro arbitro) {
        return new GetArbitroDto(arbitro.getNombre(), arbitro.getPrimerApellido(), arbitro.getSegundoApellido(), arbitro.getUsername(), arbitro.getEmail(), arbitro.getTelefono(), arbitro.getPassword(), arbitro.getRoles().toString(), arbitro.getFechaNacimiento(), arbitro.getEdad(), arbitro.getCategoria().toString(), arbitro.getFechaInscripcion(), arbitro.getTallaBotas(), arbitro.getTallaCamiseta().toString(), arbitro.getTallaCalzonas().toString(), arbitro.getTallaChandal().toString(), arbitro.getFoto());
    }

}
