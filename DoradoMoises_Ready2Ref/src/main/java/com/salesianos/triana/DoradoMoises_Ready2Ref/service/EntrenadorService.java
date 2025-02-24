package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditEntrenadorDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenador;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.UserRole;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.EntrenadorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    @Transactional
    public Entrenador createEntrenador(EditEntrenadorDto editEntrenadorDto){
        Entrenador entrenadorNuevo = Entrenador.builder()
                .nombre(editEntrenadorDto.nombre())
                .primerApellido(editEntrenadorDto.primerApellido())
                .segundoApellido(editEntrenadorDto.segundoApellido())
                .username(editEntrenadorDto.username())
                .email(editEntrenadorDto.email())
                .telefono(editEntrenadorDto.telefono())
                .password(editEntrenadorDto.password())
                .roles(Set.of(UserRole.ENTRENADOR))
                .build();

        return entrenadorRepository.save(entrenadorNuevo);
    }

}
