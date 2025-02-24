package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditEntrenadorDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.GetEntrenadorDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.EntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entrenador")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    @PostMapping("/create")
    public ResponseEntity<GetEntrenadorDto> createEntrenador(@RequestBody EditEntrenadorDto editEntrenadorDto) {
        return ResponseEntity.ok(GetEntrenadorDto.of(entrenadorService.createEntrenador(editEntrenadorDto)));
    }

}
