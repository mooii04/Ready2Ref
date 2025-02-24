package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.CreateUserRequest;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.EditArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.error.ActivationExpiredException;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.*;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.UserRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.util.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Value("${activation.duration}")
    private int activationDuration;

    public User createUser(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .username(createUserRequest.username())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .email(createUserRequest.email())
                .roles(Set.of(UserRole.USER))
                .activationToken(generateRandomActivationCode())
                .build();

        try {
            mailService.sendVerificationEmail(createUserRequest.email(), user.getActivationToken());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error al enviar el email de activación");
        }

        return userRepository.save(user);
    }

    public User createAdmin(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .username(createUserRequest.username())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .email(createUserRequest.email())
                .roles(Set.of(UserRole.ADMIN))
                .activationToken(generateRandomActivationCode())
                .build();

        try {
            mailService.sendVerificationEmail(createUserRequest.email(), user.getActivationToken());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error al enviar el email de activación");
        }

        return userRepository.save(user);
    }

    public User createEntrenador(CreateUserRequest createUserRequest){
        User user = User.builder()
                .username(createUserRequest.username())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .email(createUserRequest.email())
                .roles(Set.of(UserRole.ENTRENADOR))
                .activationToken(generateRandomActivationCode())
                .build();

        try {
            mailService.sendVerificationEmail(createUserRequest.email(), user.getActivationToken());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error al enviar el email de activación");
        }

        return userRepository.save(user);
    }

    public String generateRandomActivationCode() {
        return UUID.randomUUID().toString();
    }

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

}
