package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditArbitroUserDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.*;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.ArbitroRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.ArbitroUserSpecification;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.SearchCriteria;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArbitroService {

    private final ArbitroRepository arbitroRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Arbitro> buscarArbitros(List<SearchCriteria> criterios) {
        ArbitroUserSpecification specification = new ArbitroUserSpecification(criterios);

        Specification<Arbitro> where = specification.build();

        return arbitroRepository.findAll(where);
    }

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
                .build();

        return arbitroRepository.save(arbitroNuevo);
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
                .build();

        return arbitroRepository.save(arbitroNuevoAdmin);
    }

    @Transactional
    public Arbitro editArbitroUser(Arbitro arbitroUser, EditArbitroUserDto arbitroUserDto){
        return arbitroRepository.findFirstByUsername(arbitroUser.getUsername())
                .map(arbitro -> {
                    if(arbitroUserDto.password() != null){
                        arbitro.setPassword(passwordEncoder.encode(arbitroUserDto.password()));
                    }
                    if(arbitroUserDto.email() != null){
                        arbitro.setEmail(arbitroUserDto.email());
                    }
                    if(arbitroUserDto.telefono() != null){
                        arbitro.setTelefono(arbitroUserDto.telefono());
                    }
                    if (arbitroUserDto.username() != null){
                        arbitro.setUsername(arbitroUserDto.username());
                    }
                    if(arbitroUserDto.nombre() != null){
                        arbitro.setNombre(arbitroUserDto.nombre());
                    }
                    if(arbitroUserDto.primerApellido() != null){
                        arbitro.setPrimerApellido(arbitroUserDto.primerApellido());
                    }
                    if(arbitroUserDto.segundoApellido() != null){
                        arbitro.setSegundoApellido(arbitroUserDto.segundoApellido());
                    }
                    if(arbitroUserDto.tallaCamiseta() != null){
                        arbitro.setTallaCamiseta(Talla.valueOf(arbitroUserDto.tallaCamiseta()));
                    }
                    if(arbitroUserDto.tallaCalzonas() != null){
                        arbitro.setTallaCalzonas(Talla.valueOf(arbitroUserDto.tallaCalzonas()));
                    }
                    if(arbitroUserDto.tallaChandal() != null){
                        arbitro.setTallaChandal(Talla.valueOf(arbitroUserDto.tallaChandal()));
                    }
                    if(arbitroUserDto.foto() != null){
                        arbitro.setFoto(arbitroUserDto.foto());
                    }
                    if(arbitroUserDto.tallaBotas() != 0){
                        arbitro.setTallaBotas(arbitroUserDto.tallaBotas());
                    }

                    return arbitroRepository.save(arbitro);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Arbitro no encontrado"));
    }

}
