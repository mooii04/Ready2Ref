package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.pack.GetDtoPack;

public record EditArbitroAdminEDto(
        String nombre,
        String primerApellido,
        String segundoApellido,
        String email,
        String telefono,
        String categoria,
        String tallaBotas,
        String tallaCamiseta,
        String tallaCalzonas,
        String tallaChandal,
        String foto,
        GetDtoPack pack
) {
}
