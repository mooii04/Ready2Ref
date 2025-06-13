package com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.User;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.UserRole;

public record GetUserDto (
    UUID id,
    String nombre,
    String primerApellido,
    String segundoApellido,
    String username,
    String email,
    String telefono,
    Set<UserRole> roles,
    boolean enabled,
    Instant createdAt
) {
   
    public static GetUserDto of(User user) {
        return new GetUserDto(
            user.getId(),
            user.getNombre(),
            user.getPrimerApellido(),
            user.getSegundoApellido(),
            user.getUsername(),
            user.getEmail(),
            user.getTelefono(),
            user.getRoles(),
            user.isEnabled(),
            user.getCreatedAt()
        );
    }

}
