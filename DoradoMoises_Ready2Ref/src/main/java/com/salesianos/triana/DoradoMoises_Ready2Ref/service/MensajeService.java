package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.EditMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.GetMensajeListadoDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.FileMetadata;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.LecturaMensaje;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Mensaje;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.User;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.EntrenadorRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.LecturaMensajeRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.MensajeRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.MensajeSpecification;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.SearchCriteria;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MensajeService {

    private final MensajeRepository mensajeRepository;
    private final EntrenadorRepository entrenadorRepository;
    private final LecturaMensajeRepository lecturaMensajeRepository;

    public Mensaje save(EditMensajeDto editMensajeDto) {
        Mensaje mensaje = Mensaje.builder()
                .asunto(editMensajeDto.asunto())
                .contenido(editMensajeDto.contenido())
                .fechaEnvio(LocalDate.now())
                .build();

        return mensajeRepository.save(mensaje);
    }

    public List<GetMensajeListadoDto> buscarMensajes(List<SearchCriteria> criterios, User user) {
        MensajeSpecification specification = new MensajeSpecification(criterios);
        Specification<Mensaje> where = specification.build();

        return mensajeRepository.findAll(where).stream()
                .map(mensaje -> {
                    boolean leido = lecturaMensajeRepository
                            .findByUsuarioAndMensaje(user, mensaje)
                            .map(LecturaMensaje::isLeido)
                            .orElse(false);
                    return GetMensajeListadoDto.of(mensaje, leido);
                })
                .toList();
    }

    public void enviarMensajeEntrenoSubido(FileMetadata fileMetadata) {
    Mensaje mensaje = Mensaje.builder()
            .asunto("Nuevo entrenamiento disponible")
            .contenido("Se ha subido un nuevo archivo de entrenamiento.")
            .fechaEnvio(LocalDate.now())
            .archivoEntrenamientoId(fileMetadata.getId())
            .archivoEntrenamientoNombre(fileMetadata.getFilename())
            .build();

    mensajeRepository.save(mensaje);
}


    @Transactional
    public Mensaje marcarMensajeComoLeido(UUID id, User user) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));

        LecturaMensaje lectura = lecturaMensajeRepository.findByUsuarioAndMensaje(user, mensaje)
                .orElse(LecturaMensaje.builder()
                        .usuario(user)
                        .mensaje(mensaje)
                        .leido(false)
                        .build());

        if (!lectura.isLeido()) {
    lectura.marcarComoLeido();
    lecturaMensajeRepository.save(lectura);
}
        

        return mensaje;
    }

    public boolean comprobarSiLeidoPorUsuario(Mensaje mensaje, User user) {
    return lecturaMensajeRepository
            .findByUsuarioAndMensaje(user, mensaje)
            .map(LecturaMensaje::isLeido)
            .orElse(false);
}


}
