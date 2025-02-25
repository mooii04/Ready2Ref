package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.pack;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Pack;

public record GetDtoPack(
        String nombre,
        String descripcion,
        double precio
){

    public static GetDtoPack fromPack(Pack pack){
        return new GetDtoPack(
                pack.getNombre(),
                pack.getDescripcion(),
                pack.getPrecio()
        );
    }

    public static Pack toPack(GetDtoPack getDtoPack){
        return Pack.builder()
                .nombre(getDtoPack.nombre())
                .descripcion(getDtoPack.descripcion())
                .precio(getDtoPack.precio())
                .build();
    }

}
