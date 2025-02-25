package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit;

public record EditContraseniaDto (
        String oldPassword,
        String password,
        String verifyPassword
){
}
