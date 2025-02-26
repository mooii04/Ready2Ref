package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.search;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.asistencia.GetAsistenciaDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.pack.GetDtoPack;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;

import java.time.LocalDate;

public record GetSearchArbitroDto(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String username,
        String email,
        String telefono,
        String password,
        LocalDate fechaNacimiento,
        int edad,
        String categoria,
        LocalDate fechaInscripcion,
        int tallaBotas,
        String tallaCamiseta,
        String tallaCalzonas,
        String tallaChandal,
        String foto,
        GetAsistenciaDto asistencias,
        GetDtoPack pack
){

    public static GetSearchArbitroDto of (Arbitro a) {
        return new GetSearchArbitroDto(
                a.getNombre(),
                a.getPrimerApellido(),
                a.getSegundoApellido(),
                a.getUsername(),
                a.getEmail(),
                a.getTelefono(),
                a.getPassword(),
                a.getFechaNacimiento(),
                a.getEdad(),
                a.getCategoria().toString(),
                a.getFechaInscripcion(),
                a.getTallaBotas(),
                a.getTallaCamiseta().toString(),
                a.getTallaCalzonas().toString(),
                a.getTallaChandal().toString(),
                a.getFoto(),
                a.getAsistencias().stream().map(GetAsistenciaDto::of).findFirst().orElse(null),
                a.getPack() == null ? null : GetDtoPack.fromPack(a.getPack())
        );
    }

}
