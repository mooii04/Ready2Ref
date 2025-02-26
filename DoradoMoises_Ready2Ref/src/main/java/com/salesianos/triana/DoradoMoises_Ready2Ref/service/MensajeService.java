package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.EditMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.GetMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.search.GetSearchArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Mensaje;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.MensajeRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.MensajeSpecification;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MensajeService {

    private final MensajeRepository mensajeRepository;

    public Mensaje save(EditMensajeDto editMensajeDto) {
        Mensaje mensaje = Mensaje.builder()
                .asunto(editMensajeDto.asunto())
                .contenido(editMensajeDto.contenido())
                .fechaEnvio(editMensajeDto.fechaEnvio())
                .leido(false)
                .build();

        return mensajeRepository.save(mensaje);
    }

    public List<GetMensajeDto> buscarMensajes(List<SearchCriteria> criterios) {
        MensajeSpecification specification = new MensajeSpecification(criterios);

        Specification<Mensaje> where = specification.build();

        return mensajeRepository.findAll(where).stream()
                .map(GetMensajeDto::of)
                .toList();
    }

}