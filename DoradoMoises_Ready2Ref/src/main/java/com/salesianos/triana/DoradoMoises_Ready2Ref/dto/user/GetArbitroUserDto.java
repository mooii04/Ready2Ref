package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;

public record GetArbitroUserDto(
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

    public static GetArbitroUserDto of(Arbitro arbitro) {
        return new GetArbitroUserDto(arbitro.getNombre(), arbitro.getPrimerApellido(), arbitro.getSegundoApellido(), arbitro.getEmail(), arbitro.getTelefono(), arbitro.getPassword(), arbitro.getEdad(), arbitro.getTallaBotas(), arbitro.getTallaCamiseta().toString(), arbitro.getTallaCalzonas().toString(), arbitro.getTallaChandal().toString(), arbitro.getFoto());
    }

}
