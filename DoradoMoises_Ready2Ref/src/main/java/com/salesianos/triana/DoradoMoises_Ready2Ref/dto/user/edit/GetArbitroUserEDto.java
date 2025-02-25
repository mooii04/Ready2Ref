package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;

public record GetArbitroUserEDto(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String email,
        String telefono,
        int tallaBotas,
        String tallaCamiseta,
        String tallaCalzonas,
        String tallaChandal,
        String foto
) {

    public static GetArbitroUserEDto of(Arbitro arbitro) {
        return new GetArbitroUserEDto(
                arbitro.getNombre(),
                arbitro.getPrimerApellido(),
                arbitro.getSegundoApellido(),
                arbitro.getEmail(),
                arbitro.getTelefono(),
                arbitro.getTallaBotas(),
                arbitro.getTallaCamiseta().toString(),
                arbitro.getTallaCalzonas().toString(),
                arbitro.getTallaChandal().toString(),
                arbitro.getFoto()
        );
    }

}
