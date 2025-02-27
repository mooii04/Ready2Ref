package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditEntrenadorDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.GetArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.GetEntrenadorDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.EditEntrenadorEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.GetArbitroAdminEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Entrenador;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.EntrenadorService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entrenador")
@Tag(name = "Entrenador", description = "Operaciones relacionadas con los entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    @Operation(summary = "Crea un entrenador como usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Entrenador creado correctamente",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetEntrenadorDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Moisés",
                                                "primerApellido": "Dorado",
                                                "segundoApellido": "Gutiérrez",
                                                "username": "entrenador1",
                                                "email": "moi04@gmail.com",
                                                "telefono": "654 832 999",
                                                "password": "{bcrypt}$2a$10$XZBCfQG0ITzUNlG8Wg2lNOPHJW4SpMsNBnilaqKMkW8O0zpTC3kZW"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún entrenador",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<GetEntrenadorDto> createEntrenador(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del entrenador user", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Arbitro.class),
                    examples = @ExampleObject(value = """
                            {
                                "nombre": "Moisés",
                                "primerApellido": "Dorado",
                                "segundoApellido": "Gutiérrez",
                                "username": "entrenador1",
                                "email": "moi04@gmail.com",
                                "telefono": "654 832 999",
                                "password": "12345678",
                                "verifyPassword": "12345678"
                            }
                            """))) @RequestBody @Valid EditEntrenadorDto editEntrenadorDto) {
        return ResponseEntity.ok(GetEntrenadorDto.of(entrenadorService.createEntrenador(editEntrenadorDto)));
    }

    @Operation(summary = "Edita tu perfil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado tu perfil",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetEntrenadorDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Moisés",
                                                "primerApellido": "Dorado",
                                                "segundoApellido": "Gutiérrez",
                                                "username": "lgarcia",
                                                "email": "moi04@gmail.com",
                                                "telefono": "654 832 999",
                                                "password": "{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy"
                                            }
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado tu perfil",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content)
    })
    @PutMapping("/edit/me")
    public GetEntrenadorDto editEntrenador(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del arbitro a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetArbitroAdminEDto.class),
                    examples = @ExampleObject(value =
                            """
                                    {
                                        "nombre": "Moisés",
                                        "primerApellido": "Dorado",
                                        "segundoApellido": "Gutiérrez",
                                        "email": "moi04@gmail.com",
                                        "telefono": "654 832 999"
                                    }
                            """
                    ))) @AuthenticationPrincipal Entrenador entrenador, @RequestBody @Valid EditEntrenadorEDto editEntrenadorEDto) {
        Entrenador entrenadorEdit = entrenadorService.editEntrenadorPropio(entrenador.getId(), editEntrenadorEDto);

        return GetEntrenadorDto.of(entrenadorEdit);
    }

}
