package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.pack.GetDtoPack;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.EditArbitroAdminEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.*;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.ArbitroRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.PackRepository;
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
public class ArbitroService {

    private final ArbitroRepository arbitroRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final PackRepository packRepository;

    @Transactional
    public Arbitro createArbitroUser (EditArbitroDto arbitroDto){
        Arbitro arbitroNuevo = Arbitro.builder()
                .nombre(arbitroDto.nombre())
                .primerApellido(arbitroDto.primerApellido())
                .segundoApellido(arbitroDto.segundoApellido())
                .username(arbitroDto.username())
                .email(arbitroDto.email())
                .telefono(arbitroDto.telefono())
                .password(passwordEncoder.encode(arbitroDto.password()))
                .roles(Set.of(UserRole.USER))
                .fechaNacimiento(arbitroDto.fechaNacimiento())
                .edad(arbitroDto.edad())
                .categoria(Categoria.valueOf(arbitroDto.categoria()))
                .fechaInscripcion(arbitroDto.fechaInscripcion())
                .tallaBotas(arbitroDto.tallaBotas())
                .tallaCamiseta(Talla.valueOf(arbitroDto.tallaCamiseta()))
                .tallaCalzonas(Talla.valueOf(arbitroDto.tallaCalzonas()))
                .tallaChandal(Talla.valueOf(arbitroDto.tallaChandal()))
                .foto(arbitroDto.foto())
                .activationToken(generateRandomActivationCode())
                .build();

        try {
            mailService.sendVerificationEmail(arbitroDto.email(), arbitroNuevo.getActivationToken());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error al enviar el email de activación");
        }

        log.info("Activation token %s".formatted(arbitroNuevo.getActivationToken()));

        return arbitroRepository.save(arbitroNuevo);
    }

    public String generateRandomActivationCode() {
        return UUID.randomUUID().toString();
    }

    @Transactional
    public Arbitro createArbitroAdmin (EditArbitroDto arbitroDto){
        Arbitro arbitroNuevoAdmin = Arbitro.builder()
                .nombre(arbitroDto.nombre())
                .primerApellido(arbitroDto.primerApellido())
                .segundoApellido(arbitroDto.segundoApellido())
                .username(arbitroDto.username())
                .email(arbitroDto.email())
                .telefono(arbitroDto.telefono())
                .password(passwordEncoder.encode(arbitroDto.password()))
                .roles(Set.of(UserRole.ADMIN))
                .fechaNacimiento(arbitroDto.fechaNacimiento())
                .edad(arbitroDto.edad())
                .categoria(Categoria.valueOf(arbitroDto.categoria()))
                .fechaInscripcion(arbitroDto.fechaInscripcion())
                .tallaBotas(arbitroDto.tallaBotas())
                .tallaCamiseta(Talla.valueOf(arbitroDto.tallaCamiseta()))
                .tallaCalzonas(Talla.valueOf(arbitroDto.tallaCalzonas()))
                .tallaChandal(Talla.valueOf(arbitroDto.tallaChandal()))
                .foto(arbitroDto.foto())
                .activationToken(generateRandomActivationCode())
                .build();

        try {
            mailService.sendVerificationEmail(arbitroDto.email(), arbitroNuevoAdmin.getActivationToken());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error al enviar el email de activación");
        }

        log.info("Activation token %s".formatted(arbitroNuevoAdmin.getActivationToken()));


        return arbitroRepository.save(arbitroNuevoAdmin);
    }

    /*
    public Arbitro editArbitroUser (EditArbitroUserDto arbitroUserDto){
        
    }*/

    public Arbitro editArbitroAdmin(UUID userId, EditArbitroAdminEDto editArbitroAdminEDto) {
        Arbitro arbitro = arbitroRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Arbitro no encontrado"));

        // Actualizar los campos del usuario
        arbitro.setNombre(editArbitroAdminEDto.nombre());
        arbitro.setPrimerApellido(editArbitroAdminEDto.primerApellido());
        arbitro.setSegundoApellido(editArbitroAdminEDto.segundoApellido());
        arbitro.setEmail(editArbitroAdminEDto.email());
        arbitro.setTelefono(editArbitroAdminEDto.telefono());
        arbitro.setCategoria(Categoria.valueOf(editArbitroAdminEDto.categoria()));
        arbitro.setTallaBotas(Integer.parseInt(editArbitroAdminEDto.tallaBotas()));
        arbitro.setTallaCamiseta(Talla.valueOf(editArbitroAdminEDto.tallaCamiseta()));
        arbitro.setTallaCalzonas(Talla.valueOf(editArbitroAdminEDto.tallaCalzonas()));
        arbitro.setTallaChandal(Talla.valueOf(editArbitroAdminEDto.tallaChandal()));
        arbitro.setFoto(editArbitroAdminEDto.foto());

        Pack pack = packRepository.findByNombre(editArbitroAdminEDto.pack().nombre())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pack no encontrado"));

        arbitro.setPack(pack);


        return arbitroRepository.save(arbitro);
    }

}
