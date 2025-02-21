package com.salesianos.triana.DoradoMoises_Ready2Ref.dto;

public record CreateUserRequest(
        String email, String username, String password, String verifyPassword
) {
}