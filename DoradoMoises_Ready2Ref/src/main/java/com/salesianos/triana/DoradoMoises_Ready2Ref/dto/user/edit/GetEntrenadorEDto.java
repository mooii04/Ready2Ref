package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenador;

public record GetEntrenadorEDto(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String email,
        String telefono
) {

    public static GetEntrenadorEDto of(Entrenador entrenador){
        return new GetEntrenadorEDto(
                entrenador.getNombre(),
                entrenador.getPrimerApellido(),
                entrenador.getSegundoApellido(),
                entrenador.getEmail(),
                entrenador.getTelefono()
        );
    }

}
