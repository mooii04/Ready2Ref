package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Asistencia;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.AsistenciaService;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;
    private final ArbitroService arbitroService;

    // Crear asistencia (añadir árbitro a entreno)
    @PostMapping("/")
    public ResponseEntity<Asistencia> addAsistencia(@RequestBody Asistencia asistencia, Principal principal) {
        Arbitro arbitro = arbitroService.findByUsername(principal.getName());
        asistencia.setUsuario(arbitro);
        return ResponseEntity.ok(asistenciaService.save(asistencia));
    }

    // Ver asistencias propias
    @GetMapping("/me")
    public ResponseEntity<List<Asistencia>> getMisAsistencias(Principal principal) {
        Arbitro arbitro = arbitroService.findByUsername(principal.getName());
        return ResponseEntity.ok(asistenciaService.findByUsuario(arbitro));
    }

    // Ver todas las asistencias (solo admin)
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Asistencia>> getAllAsistencias() {
        return ResponseEntity.ok(asistenciaService.findAll());
    }
}
