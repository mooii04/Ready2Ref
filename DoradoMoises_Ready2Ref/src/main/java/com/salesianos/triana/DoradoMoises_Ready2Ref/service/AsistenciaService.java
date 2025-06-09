package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.asistencia.EditAsistenciaDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Asistencia;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.AsistenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public Asistencia establecerAsistencia(EditAsistenciaDto editAsistenciaDto) {
        Asistencia asistencia = new Asistencia().builder()
                .idAsistencia(editAsistenciaDto.idAsistencia())

                .build();

        return asistenciaRepository.save(asistencia);
    }

    public Asistencia save(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    public List<Asistencia> findByUsuario(Arbitro arbitro) {
        return asistenciaRepository.findByUsuario(arbitro);
    }

    public List<Asistencia> findAll() {
        return asistenciaRepository.findAll();
    }

    public Asistencia findById(UUID id) {
        return asistenciaRepository.findById(id).orElse(null);
    }
}
