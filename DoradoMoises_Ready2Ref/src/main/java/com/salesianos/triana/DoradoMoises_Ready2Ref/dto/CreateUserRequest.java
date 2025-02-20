package com.salesianos.triana.DoradoMoises_Ready2Ref.dto;

public record CreateUserRequest(
        String username, String password, String verifyPassword
) {
}