package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.EditMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.GetMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.GetArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.search.GetSearchArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/mensaje")
@Tag(name = "Mensaje", description = "Gestión de los mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    @Operation(summary = "Crea un mensaje")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Mensaje creado correctamente",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetMensajeDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "asunto": "Cena de Navidad",
                                                "contenido": "Buenas tardes a todos, en estos próximos días os iremos comentando más información de la cena de Navidad del día 22 de Diciembre",
                                                "fechaEnvio": "2025-02-27",
                                                "leido": false
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún mensaje",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content)
    })
    @PostMapping("/create/admin")
    public ResponseEntity<GetMensajeDto> createMensajeAdmin(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del mensaje", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Arbitro.class),
                    examples = @ExampleObject(value = """
                            {
                                "asunto": "Cena de Navidad",
                                "contenido": "Buenas tardes a todos, en estos próximos días os iremos comentando más información de la cena de Navidad del día 22 de Diciembre"
                            }
                            """))) @RequestBody @Valid EditMensajeDto editMensajeDto) {
        return ResponseEntity.ok(GetMensajeDto.of(mensajeService.save(editMensajeDto)));
    }

    @Operation(summary = "Busca mensajes según criterios de búsqueda", description = "Permite filtrar mensajes mediante parámetros opcionales en la consulta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de mensajes encontrados según los criterios de búsqueda",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetMensajeDto.class)),
                            examples = {@ExampleObject(
                                    value =
                                            """
                                                    [
                                                        {
                                                            "asunto": "Entrenamiento Semana 23-29 Diciembre",
                                                            "contenido": "Buenas tardes compaÃ±eros, el entreno de esta semana prÃ³xima ya esta subido a la plataforma",
                                                            "fechaEnvio": "2025-02-25",
                                                            "leido": false
                                                        },
                                                        {
                                                            "asunto": "Entrenamiento subido",
                                                            "contenido": "Se ha subido un nuevo entrenamiento a la plataforma, por favor eche un vistazo",
                                                            "fechaEnvio": "2025-02-27",
                                                            "leido": false
                                                        },
                                                        {
                                                            "asunto": "Cena de Navidad",
                                                            "contenido": "Buenas tardes a todos, en estos próximos días os iremos comentando más información de la cena de Navidad del día 22 de Diciembre",
                                                            "fechaEnvio": "2025-02-27",
                                                            "leido": false
                                                        }
                                                    ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400", description = "Los parámetros de búsqueda no son válidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se han encontrado mensajes con los criterios dados", content = @Content),
            @ApiResponse(responseCode = "401", description = "No tienes permisos para acceder a este recurso", content = @Content),
            @ApiResponse(responseCode = "403", description = "No tienes permisos para acceder a este recurso", content = @Content)
    })
    @GetMapping("/search")
    public List<GetMensajeDto> buscar(@RequestParam(value="search", required = false) String search) {
        log.info(search);
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                log.info(matcher.group(1));
                log.info(matcher.group(2));
                log.info(matcher.group(3));
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }

        return mensajeService.buscarMensajes(params);

    }

}
