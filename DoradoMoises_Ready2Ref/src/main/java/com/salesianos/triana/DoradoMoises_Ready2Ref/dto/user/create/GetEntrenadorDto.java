package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenador;

public record GetEntrenadorDto (
        String nombre,
        String primerApellido,
        String segundoApellido,
        String username,
        String email,
        String telefono,
        String password
){

    public static GetEntrenadorDto of(Entrenador entrenador) {
        return new GetEntrenadorDto(entrenador.getNombre(), entrenador.getPrimerApellido(), entrenador.getSegundoApellido(), entrenador.getUsername(), entrenador.getEmail(), entrenador.getTelefono(), entrenador.getPassword());
    }

}
