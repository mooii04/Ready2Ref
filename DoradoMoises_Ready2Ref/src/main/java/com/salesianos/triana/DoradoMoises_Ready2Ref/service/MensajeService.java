package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.EditMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.GetMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenador;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Mensaje;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.EntrenadorRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.MensajeRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.MensajeSpecification;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MensajeService {

    private final MensajeRepository mensajeRepository;
    private final EntrenadorRepository entrenadorRepository;

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

    public void enviarMensajeEntrenoSubido() {
        Mensaje mensaje = Mensaje.builder()
                .asunto("Entrenamiento subido")
                .contenido("Se ha subido un nuevo entrenamiento a la plataforma, por favor eche un vistazo")
                .fechaEnvio(LocalDate.now())
                .leido(false)
                .build();

        mensajeRepository.save(mensaje);
    }

}