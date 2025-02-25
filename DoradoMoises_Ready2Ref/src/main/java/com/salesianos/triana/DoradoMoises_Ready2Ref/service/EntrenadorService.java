package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditEntrenadorDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.EditArbitroUserEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.EditEntrenadorEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenador;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Talla;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.UserRole;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.EntrenadorRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.util.MailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Transactional
    public Entrenador createEntrenador(EditEntrenadorDto editEntrenadorDto){
        Entrenador entrenadorNuevo = Entrenador.builder()
                .nombre(editEntrenadorDto.nombre())
                .primerApellido(editEntrenadorDto.primerApellido())
                .segundoApellido(editEntrenadorDto.segundoApellido())
                .username(editEntrenadorDto.username())
                .email(editEntrenadorDto.email())
                .telefono(editEntrenadorDto.telefono())
                .password(passwordEncoder.encode(editEntrenadorDto.password()))
                .roles(Set.of(UserRole.ENTRENADOR))
                .activationToken(generateRandomActivationCode())
                .build();

        try {
            mailService.sendVerificationEmail(editEntrenadorDto.email(), entrenadorNuevo.getActivationToken());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error al enviar el email de activación");
        }

        log.info("Activation token %s".formatted(entrenadorNuevo.getActivationToken()));


        return entrenadorRepository.save(entrenadorNuevo);
    }

    public String generateRandomActivationCode() {
        return UUID.randomUUID().toString();
    }

    public Entrenador editEntrenadorPropio(UUID userId, EditEntrenadorEDto editEntrenadorEDto){
        Entrenador entrenador = entrenadorRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrenador no encontrado"));

        entrenador.setNombre(editEntrenadorEDto.nombre());
        entrenador.setPrimerApellido(editEntrenadorEDto.primerApellido());
        entrenador.setSegundoApellido(editEntrenadorEDto.segundoApellido());
        entrenador.setEmail(editEntrenadorEDto.email());
        entrenador.setTelefono(editEntrenadorEDto.telefono());
        entrenador.setEntrenamientos(entrenador.getEntrenamientos());

        return entrenadorRepository.save(entrenador);
    }

}
