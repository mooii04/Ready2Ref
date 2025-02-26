package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.EditMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.GetMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.MensajeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mensaje")
public class MensajeController {

    private final MensajeService mensajeService;

    @PostMapping("/create/admin")
    public ResponseEntity<GetMensajeDto> createMensajeAdmin(@RequestBody @Valid EditMensajeDto editMensajeDto) {
        return ResponseEntity.ok(GetMensajeDto.of(mensajeService.save(editMensajeDto)));
    }

}
