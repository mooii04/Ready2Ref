package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditEntrenadorDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.GetEntrenadorDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.EditEntrenadorEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenador;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.EntrenadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entrenador")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    @PostMapping("/create")
    public ResponseEntity<GetEntrenadorDto> createEntrenador(@RequestBody EditEntrenadorDto editEntrenadorDto) {
        return ResponseEntity.ok(GetEntrenadorDto.of(entrenadorService.createEntrenador(editEntrenadorDto)));
    }

    @PutMapping("/edit/me")
    public GetEntrenadorDto editEntrenador(@AuthenticationPrincipal Entrenador entrenador, @RequestBody @Valid EditEntrenadorEDto editEntrenadorEDto) {
        Entrenador entrenadorEdit = entrenadorService.editEntrenadorPropio(entrenador.getId(), editEntrenadorEDto);

        return GetEntrenadorDto.of(entrenadorEdit);
    }

}
