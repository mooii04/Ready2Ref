package com.salesianos.triana.DoradoMoises_Ready2Ref.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.User;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String token,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String refreshToken

) {

    public static UserResponse of (User user) {
        return new UserResponse(user.getId(), user.getEmail(), null, null);
    }

    public static UserResponse of (User user, String token, String refreshToken) {
        return new UserResponse(user.getId(), user.getEmail(), token, refreshToken);
    }

}
