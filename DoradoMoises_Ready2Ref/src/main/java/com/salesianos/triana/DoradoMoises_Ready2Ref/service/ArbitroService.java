package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.EditArbitroAdminEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.EditArbitroUserEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.search.GetSearchArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.*;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.ArbitroRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.PackRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.ArbitroUserSpecification;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.SearchCriteria;
import com.salesianos.triana.DoradoMoises_Ready2Ref.util.MailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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

    public Arbitro editArbitroAdmin(String username, EditArbitroAdminEDto editArbitroAdminEDto) {
        Arbitro arbitro = arbitroRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Arbitro no encontrado"));

        // Actualizar los campos del usuario
        arbitro.setNombre(editArbitroAdminEDto.nombre());
        arbitro.setPrimerApellido(editArbitroAdminEDto.primerApellido());
        arbitro.setSegundoApellido(editArbitroAdminEDto.segundoApellido());
        arbitro.setEmail(editArbitroAdminEDto.email());
        arbitro.setTelefono(editArbitroAdminEDto.telefono());
        arbitro.setCategoria(Categoria.valueOf(editArbitroAdminEDto.categoria()));
        arbitro.setTallaBotas(editArbitroAdminEDto.tallaBotas());
        arbitro.setTallaCamiseta(Talla.valueOf(editArbitroAdminEDto.tallaCamiseta()));
        arbitro.setTallaCalzonas(Talla.valueOf(editArbitroAdminEDto.tallaCalzonas()));
        arbitro.setTallaChandal(Talla.valueOf(editArbitroAdminEDto.tallaChandal()));
        arbitro.setFoto(editArbitroAdminEDto.foto());

        if(arbitro.getPack() == null){
            Pack pack = Pack.builder()
                    .nombre(editArbitroAdminEDto.pack().nombre())
                    .descripcion(editArbitroAdminEDto.pack().descripcion())
                    .precio(editArbitroAdminEDto.pack().precio())
                    .build();
            packRepository.save(pack);
        }

        Pack pack = packRepository.findByNombre(editArbitroAdminEDto.pack().nombre())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pack no encontrado"));

        arbitro.setPack(pack);

        return arbitroRepository.save(arbitro);
    }

    public Arbitro editArbitroUserPropio(UUID userId, EditArbitroUserEDto editArbitroUserEDto){
        Arbitro arbitro = arbitroRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Arbitro no encontrado"));

        arbitro.setNombre(editArbitroUserEDto.nombre());
        arbitro.setPrimerApellido(editArbitroUserEDto.primerApellido());
        arbitro.setSegundoApellido(editArbitroUserEDto.segundoApellido());
        arbitro.setEmail(editArbitroUserEDto.email());
        arbitro.setTelefono(editArbitroUserEDto.telefono());
        arbitro.setTallaBotas(editArbitroUserEDto.tallaBotas());
        arbitro.setTallaCamiseta(Talla.valueOf(editArbitroUserEDto.tallaCamiseta()));
        arbitro.setTallaCalzonas(Talla.valueOf(editArbitroUserEDto.tallaCalzonas()));
        arbitro.setTallaChandal(Talla.valueOf(editArbitroUserEDto.tallaChandal()));
        arbitro.setFoto(editArbitroUserEDto.foto());

        return arbitroRepository.save(arbitro);
    }

    @Transactional
    public Arbitro editUser(String username, EditArbitroAdminEDto editArbitroAdminEDto) {

        return arbitroRepository.findByUsername(username)
                .map(arbitro -> {
                    if (editArbitroAdminEDto.email() != null) {
                        arbitro.setEmail(editArbitroAdminEDto.email());
                    }
                    if (editArbitroAdminEDto.nombre() != null) {
                        arbitro.setNombre(editArbitroAdminEDto.nombre());
                    }
                    if (editArbitroAdminEDto.primerApellido() != null) {
                        arbitro.setPrimerApellido(editArbitroAdminEDto.primerApellido());
                    }
                    if (editArbitroAdminEDto.segundoApellido() != null) {
                        arbitro.setSegundoApellido(editArbitroAdminEDto.segundoApellido());
                    }
                    if (editArbitroAdminEDto.telefono() != null) {
                        arbitro.setTelefono(editArbitroAdminEDto.telefono());
                    }
                    if (editArbitroAdminEDto.categoria() != null) {
                        arbitro.setCategoria(Categoria.valueOf(editArbitroAdminEDto.categoria()));
                    }
                    if (editArbitroAdminEDto.tallaBotas() != 0) {
                        arbitro.setTallaBotas(editArbitroAdminEDto.tallaBotas());
                    }
                    if (editArbitroAdminEDto.tallaCamiseta() != null) {
                        arbitro.setTallaCamiseta(Talla.valueOf(editArbitroAdminEDto.tallaCamiseta()));
                    }
                    if (editArbitroAdminEDto.tallaCalzonas() != null) {
                        arbitro.setTallaCalzonas(Talla.valueOf(editArbitroAdminEDto.tallaCalzonas()));
                    }
                    if (editArbitroAdminEDto.tallaChandal() != null) {
                        arbitro.setTallaChandal(Talla.valueOf(editArbitroAdminEDto.tallaChandal()));
                    }
                    if (editArbitroAdminEDto.foto() != null) {
                        arbitro.setFoto(editArbitroAdminEDto.foto());
                    }
                    if (editArbitroAdminEDto.pack() != null) {
                        Pack pack = Pack.builder()
                                .nombre(editArbitroAdminEDto.pack().nombre())
                                .descripcion(editArbitroAdminEDto.pack().descripcion())
                                .precio(editArbitroAdminEDto.pack().precio())
                                .build();
                        packRepository.save(pack);
                        arbitro.setPack(pack);
                    }

                    return arbitro;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

    public List<GetSearchArbitroDto> buscarArbitros(List<SearchCriteria> criterios) {
        ArbitroUserSpecification specification = new ArbitroUserSpecification(criterios);

        Specification<Arbitro> where = specification.build();

        return arbitroRepository.findAll(where).stream()
                .map(GetSearchArbitroDto::of)
                .toList();
    }

}
