package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.asistencia.CreateAsistenciaDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.asistencia.GetAsistenciaDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.ArbitroService;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.AsistenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;
    private final ArbitroService arbitroService;

    // Admin: Añadir asistencia para un árbitro a un entrenamiento
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAsistencia(@RequestBody CreateAsistenciaDto dto) {
        return ResponseEntity.ok(asistenciaService.save(dto));
    }

    // Árbitro: Ver sus asistencias
    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<GetAsistenciaDto>> getMisAsistencias(Principal principal) {
        Arbitro arbitro = arbitroService.findByUsername(principal.getName());
        return ResponseEntity.ok(asistenciaService.findByArbitro(arbitro));
    }

    // Admin: Ver todas las asistencias
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GetAsistenciaDto>> getAllAsistencias() {
        return ResponseEntity.ok(asistenciaService.findAll());
    }
}
