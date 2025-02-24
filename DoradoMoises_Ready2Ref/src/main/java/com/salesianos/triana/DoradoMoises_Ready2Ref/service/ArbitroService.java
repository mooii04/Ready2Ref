package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditArbitroUserDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Categoria;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Talla;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.UserRole;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.ArbitroRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.ArbitroUserSpecification;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
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

    public Arbitro editArbitroUser (String username, EditArbitroUserDto arbitroUserDto){
        Optional<Arbitro> arbitroOptional = Optional.ofNullable(arbitroRepository.findArbitroByUsername(username));

        //PONER BIEN EL MENSAJE DE ERROR
        if(arbitroOptional.isEmpty())
            throw new EntityNotFoundException("No se ha encontrado el árbitro con username: " + username);

        Arbitro arbitro = arbitroOptional.get();

        arbitro.setNombre(arbitroUserDto.nombre());
        arbitro.setPrimerApellido(arbitroUserDto.primerApellido());
        arbitro.setSegundoApellido(arbitroUserDto.segundoApellido());
        arbitro.setEmail(arbitroUserDto.email());
        arbitro.setTelefono(arbitroUserDto.telefono());
        arbitro.setPassword(passwordEncoder.encode(arbitroUserDto.password()));
        arbitro.setEdad(arbitroUserDto.edad());
        arbitro.setTallaBotas(arbitroUserDto.tallaBotas());
        arbitro.setTallaCamiseta(Talla.valueOf(arbitroUserDto.tallaCamiseta()));
        arbitro.setTallaCalzonas(Talla.valueOf(arbitroUserDto.tallaCalzonas()));
        arbitro.setTallaChandal(Talla.valueOf(arbitroUserDto.tallaChandal()));
        arbitro.setFoto(arbitroUserDto.foto());

        return arbitroRepository.save(arbitro);
    }

}
