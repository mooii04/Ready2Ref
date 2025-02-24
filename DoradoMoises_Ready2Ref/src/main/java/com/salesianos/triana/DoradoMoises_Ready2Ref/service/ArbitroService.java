package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.EditArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Categoria;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Talla;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.UserRole;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.ArbitroRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArbitroService {

    private final ArbitroRepository arbitroRepository;
    private final PasswordEncoder passwordEncoder;

    public Arbitro createArbitroUser (EditArbitroDto arbitroDto){
        Arbitro arbitro = Arbitro.builder()
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

        return arbitroRepository.save(arbitro);
    }

}
