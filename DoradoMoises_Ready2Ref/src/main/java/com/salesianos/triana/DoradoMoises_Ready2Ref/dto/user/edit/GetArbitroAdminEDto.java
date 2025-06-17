package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.pack.GetDtoPack;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;

public record GetArbitroAdminEDto(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String email,
        String telefono,
        String categoria,
        int tallaBotas,
        String tallaCamiseta,
        String tallaCalzonas,
        String tallaChandal,
        String foto
) {

    public static GetArbitroAdminEDto of(Arbitro arbitro){
        return new GetArbitroAdminEDto(
                arbitro.getNombre(),
                arbitro.getPrimerApellido(),
                arbitro.getSegundoApellido(),
                arbitro.getEmail(),
                arbitro.getTelefono(),
                arbitro.getCategoria().toString(),
                arbitro.getTallaBotas(),
                arbitro.getTallaCamiseta().toString(),
                arbitro.getTallaCalzonas().toString(),
                arbitro.getTallaChandal().toString(),
                arbitro.getFoto()
        );
    }

}
