package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.User;

public record GetContraseniaDto(
        String oldPassword,
        String password,
        String verifyPassword
) {

    public static GetContraseniaDto of(User user) {
        return new GetContraseniaDto(
                user.getPassword(),
                user.getPassword(),
                user.getPassword()
        );
    }

}
