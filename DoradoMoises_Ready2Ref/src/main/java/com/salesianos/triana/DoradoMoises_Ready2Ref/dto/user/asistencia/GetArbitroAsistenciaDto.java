package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.asistencia;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;

public record GetArbitroAsistenciaDto(
        String username,
        String nombre,
        String primerApellido,
        String segundoApellido,
        String categoria
) {

    public static GetArbitroAsistenciaDto of(Arbitro arbitro) {
        return new GetArbitroAsistenciaDto(arbitro.getUsername(), arbitro.getNombre(), arbitro.getPrimerApellido(), arbitro.getSegundoApellido(), arbitro.getCategoria().toString());
    }

}
