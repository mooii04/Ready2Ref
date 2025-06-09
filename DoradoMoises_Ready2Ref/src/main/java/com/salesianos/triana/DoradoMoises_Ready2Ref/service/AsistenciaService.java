package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.asistencia.CreateAsistenciaDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.asistencia.GetAsistenciaDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Asistencia;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenamiento;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.AsistenciaRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.ArbitroRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.EntrenamientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final EntrenamientoRepository entrenamientoRepository;
    private final ArbitroRepository arbitroRepository;

    public Asistencia save(CreateAsistenciaDto dto) {
        Entrenamiento entrenamiento = entrenamientoRepository.findById(dto.entrenamientoId()).orElseThrow();
        Arbitro arbitro = arbitroRepository.findById(dto.arbitroId()).orElseThrow();
        Asistencia asistencia = Asistencia.builder()
                .entrenamiento(entrenamiento)
                .arbitro(arbitro)
                .asistio(dto.asistio())
                .build();
        return asistenciaRepository.save(asistencia);
    }

    public List<GetAsistenciaDto> findByArbitro(Arbitro arbitro) {
        return asistenciaRepository.findByArbitro(arbitro)
                .stream()
                .map(GetAsistenciaDto::of)
                .toList();
    }

    public List<GetAsistenciaDto> findAll() {
        return asistenciaRepository.findAll()
                .stream()
                .map(GetAsistenciaDto::of)
                .toList();
    }

    public Asistencia findById(UUID id) {
        return asistenciaRepository.findById(id).orElse(null);
    }
}
