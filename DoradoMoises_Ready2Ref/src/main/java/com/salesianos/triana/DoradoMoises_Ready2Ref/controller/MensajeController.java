package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.EditMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.GetMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.GetMensajeListadoDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Mensaje;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.User;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.MensajeService;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/mensaje")
@Tag(name = "Mensaje", description = "Gestión de los mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    @PostMapping("/create/admin")
    public ResponseEntity<GetMensajeDto> createMensajeAdmin(@RequestBody @Valid EditMensajeDto editMensajeDto) {
        return ResponseEntity.ok(GetMensajeDto.of(mensajeService.save(editMensajeDto)));
    }

    @GetMapping("/search")
    public List<GetMensajeListadoDto> buscar(@RequestParam(value = "search", required = false) String search,
                                             @AuthenticationPrincipal User user) {
        log.info(search);
        List<SearchCriteria> params = new ArrayList<>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }

        return mensajeService.buscarMensajes(params, user);
    }

    @PutMapping("/{id}/leido")
    public ResponseEntity<GetMensajeListadoDto> marcarComoLeido(@PathVariable UUID id,
                                                            @AuthenticationPrincipal User user) {
        Mensaje mensaje = mensajeService.marcarMensajeComoLeido(id, user);
        boolean leido = mensajeService.comprobarSiLeidoPorUsuario(mensaje, user);
        return ResponseEntity.ok(GetMensajeListadoDto.of(mensaje, leido));
    }
}
