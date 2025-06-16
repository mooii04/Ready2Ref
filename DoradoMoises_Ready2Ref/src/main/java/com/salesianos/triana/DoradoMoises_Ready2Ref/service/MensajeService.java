package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.EditMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.GetMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.GetMensajeListadoDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenador;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Mensaje;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.User;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.EntrenadorRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.MensajeRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.MensajeSpecification;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.SearchCriteria;

import jakarta.transaction.Transactional;
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
                .fechaEnvio(LocalDate.now())
                .leido(false)
                .build();

        return mensajeRepository.save(mensaje);
    }

    public List<GetMensajeListadoDto> buscarMensajes(List<SearchCriteria> criterios) {
        MensajeSpecification specification = new MensajeSpecification(criterios);

        Specification<Mensaje> where = specification.build();

        return mensajeRepository.findAll(where).stream()
                .map(GetMensajeListadoDto::of)
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

    // Cambia el método para que devuelva Mensaje en vez de void
    @Transactional
    public Mensaje marcarMensajeComoLeido(UUID id, User user) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
        mensaje.setLeido(true);
        mensajeRepository.save(mensaje);
        return mensaje;
    }

}