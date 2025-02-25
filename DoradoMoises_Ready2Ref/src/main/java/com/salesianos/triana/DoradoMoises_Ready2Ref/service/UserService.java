package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.CreateUserRequest;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.EditContraseniaDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.error.ActivationExpiredException;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.*;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.UserRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.util.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Value("${activation.duration}")
    private int activationDuration;

    public User activateAccount(String token) {

        return userRepository.findByActivationToken(token)
                .filter(user -> ChronoUnit.MINUTES.between(Instant.now(), user.getCreatedAt()) - activationDuration < 0)
                .map(user -> {
                    user.setEnabled(true);
                    user.setActivationToken(null);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ActivationExpiredException("El código de activación no existe o ha caducado"));
    }

    public User updateUserPassword(UUID userId, EditContraseniaDto editContraseniaDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        //Verificar que las contraseñas coinciden
        if (!editContraseniaDto.password().equals(editContraseniaDto.verifyPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las contraseñas no coinciden");
        }

        // Verificar que la contraseña antigua es correcta
        if (!passwordEncoder.matches(editContraseniaDto.oldPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contraseña antigua incorrecta");
        }

        // Actualizar la contraseña
        user.setPassword(passwordEncoder.encode(editContraseniaDto.password()));

        return userRepository.save(user);
    }

}
