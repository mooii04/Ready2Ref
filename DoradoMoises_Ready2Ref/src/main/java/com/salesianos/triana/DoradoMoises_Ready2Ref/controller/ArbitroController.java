package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.GetArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.*;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.search.GetSearchArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.ArbitroService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/arbitro")
@Tag(name = "Árbitro", description = "Operaciones relacionadas con los árbitros")
public class ArbitroController {

    private final ArbitroService arbitroService;


    @Operation(summary = "Crea un árbitro como usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Árbitro creado correctamente",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetArbitroDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Moisés",
                                                "primerApellido": "Dorado",
                                                "segundoApellido": "Gutiérrez",
                                                "username": "username1234",
                                                "email": "dorado.gumoi24@triana.salesianos.edu",
                                                "telefono": "654 832 999",
                                                "password": "{bcrypt}$2a$10$eiUaG5ArHWj4SwbmIxreNuh18mKmt3ownABFe8xJ9/OSP/ha3AdZ2",
                                                "roles": "[USER]",
                                                "fechaNacimiento": "2003-01-01",
                                                "edad": 20,
                                                "categoria": "DIVISION_HONOR",
                                                "fechaInscripcion": "2023-10-01",
                                                "tallaBotas": 44,
                                                "tallaCamiseta": "S",
                                                "tallaCalzonas": "S",
                                                "tallaChandal": "M",
                                                "foto": "fotomooii04.png"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún árbitro",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content)
    })
    @PostMapping("/create/user")
    public ResponseEntity<GetArbitroDto> createArbitroUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del árbitro user", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Arbitro.class),
                    examples = @ExampleObject(value = """
                            {
                                "nombre": "Moisés",
                                "primerApellido": "Dorado",
                                "segundoApellido": "Gutiérrez",
                                "username": "username1234",
                                "email": "dorado.gumoi24@triana.salesianos.edu",
                                "telefono": "654 832 999",
                                "password": "12345678",
                                "verifyPassword": "12345678",
                                "fechaNacimiento": "2003-01-01",
                                "edad": 20,
                                "categoria": "DIVISION_HONOR",
                                "fechaInscripcion": "2023-10-01",
                                "tallaBotas": 44,
                                "tallaCamiseta": "S",
                                "tallaCalzonas": "S",
                                "tallaChandal": "M",
                                "foto": "fotomooii04.png"
                            }
                            """))) @RequestBody @Valid EditArbitroDto editUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetArbitroDto.of(arbitroService.createArbitroUser(editUserDto)));
    }

    @Operation(summary = "Crea un árbitro como admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Árbitro creado correctamente",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetArbitroDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Moisés",
                                                "primerApellido": "Dorado",
                                                "segundoApellido": "Gutiérrez",
                                                "username": "username1",
                                                "email": "dorado.gumoi24@triana.salesianos.edu",
                                                "telefono": "654 832 999",
                                                "password": "{bcrypt}$2a$10$0N7pUtMfcCIBSH2K2BYeRu8JEluhO4N.CeOASMPe0HKRqiWajOPRK",
                                                "roles": "[ADMIN]",
                                                "fechaNacimiento": "2003-01-01",
                                                "edad": 20,
                                                "categoria": "DIVISION_HONOR",
                                                "fechaInscripcion": "2023-10-01",
                                                "tallaBotas": 44,
                                                "tallaCamiseta": "S",
                                                "tallaCalzonas": "S",
                                                "tallaChandal": "M",
                                                "foto": "fotomooii04.png"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún árbitro",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content)
    })
    @PostMapping("/create/admin")
    public ResponseEntity<GetArbitroDto> createArbitroAdmin(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del árbitro user", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Arbitro.class),
                    examples = @ExampleObject(value = """
                            {
                                "nombre": "Moisés",
                                "primerApellido": "Dorado",
                                "segundoApellido": "Gutiérrez",
                                "username": "username1",
                                "email": "dorado.gumoi24@triana.salesianos.edu",
                                "telefono": "654 832 999",
                                "password": "12345678",
                                "verifyPassword": "12345678",
                                "fechaNacimiento": "2003-01-01",
                                "edad": 20,
                                "categoria": "DIVISION_HONOR",
                                "fechaInscripcion": "2023-10-01",
                                "tallaBotas": 44,
                                "tallaCamiseta": "S",
                                "tallaCalzonas": "S",
                                "tallaChandal": "M",
                                "foto": "fotomooii04.png"
                            }
                            """))) @RequestBody @Valid EditArbitroDto editUserDto) {
        return ResponseEntity.ok(GetArbitroDto.of(arbitroService.createArbitroAdmin(editUserDto)));
    }

    @Operation(summary = "Edita tu perfil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado tu perfil",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetArbitroAdminEDto.class)),
                            examples = {@ExampleObject(
                                value = """
                                        {
                                            "nombre": "Moisés",
                                            "primerApellido": "Dorado",
                                            "segundoApellido": "Gutiérrez",
                                            "email": "moi04@gmail.com",
                                            "telefono": "654 832 999",
                                            "categoria": "OFICIAL",
                                            "tallaBotas": 44,
                                            "tallaCamiseta": "S",
                                            "tallaCalzonas": "S",
                                            "tallaChandal": "M",
                                            "foto": "hola.png",
                                            "getDtoPack": {
                                                "nombre": "Pack Básico",
                                                "descripcion": "Incluye 5 entrenamientos",
                                                "precio": 200.0
                                            }
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
    @PutMapping("/edit/admin/me")
    public GetArbitroAdminEDto editAdmin(@io.swagger.v3.oas.annotations.parameters.RequestBody(
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
                                        "telefono": "654 832 999",
                                        "tallaBotas": 44,
                                        "tallaCamiseta": "S",
                                        "tallaCalzonas": "S",
                                        "tallaChandal": "M",
                                        "foto": "hola.png",
                                        "categoria": "OFICIAL",
                                        "pack": {
                                            "nombre": "Pack Básico",
                                            "descripcion": "Incluye 5 entrenamientos",
                                            "precio": 200
                                        }
                                    }
                            """
                    ))) @AuthenticationPrincipal Arbitro arbitro, @RequestBody EditArbitroAdminEDto editArbitroAdminEDto) {
        Arbitro arbitroEdit = arbitroService.editArbitroAdmin(arbitro.getUsername(), editArbitroAdminEDto);

        return GetArbitroAdminEDto.of(arbitroEdit);
    }

    @Operation(summary = "Edita tu perfil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado tu perfil",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetArbitroUserEDto.class)),
                            examples = {@ExampleObject(
                                    value =
                                        """
                                                {
                                                    "nombre": "Moisés",
                                                    "primerApellido": "Dorado",
                                                    "segundoApellido": "Gutiérrez",
                                                    "email": "moi04@gmail.com",
                                                    "telefono": "654 832 999",
                                                    "tallaBotas": 44,
                                                    "tallaCamiseta": "S",
                                                    "tallaCalzonas": "S",
                                                    "tallaChandal": "M",
                                                    "foto": "hola.png"
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
    @PutMapping("/edit/user/me")
    public GetArbitroUserEDto editUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(
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
                                        "telefono": "654 832 999",
                                        "tallaBotas": 44,
                                        "tallaCamiseta": "S",
                                        "tallaCalzonas": "S",
                                        "tallaChandal": "M",
                                        "foto": "hola.png"
                                    }
                            """
                    ))) @AuthenticationPrincipal Arbitro arbitro, @RequestBody @Valid EditArbitroUserEDto editArbitroUserEDto) {
        Arbitro arbitroEdit = arbitroService.editArbitroUserPropio(arbitro.getId(), editArbitroUserEDto);

        return GetArbitroUserEDto.of(arbitroEdit);
    }

    @Operation(summary = "Edita un perfil de árbitro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el perfil",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetArbitroAdminEDto.class)),
                            examples = {@ExampleObject(
                                    value =
                                            """
                                                    {
                                                        "nombre": "Moisés",
                                                        "primerApellido": "Dorado",
                                                        "segundoApellido": "Gutiérrez",
                                                        "email": "moi04@gmail.com",
                                                        "telefono": "654 832 999",
                                                        "categoria": "OFICIAL",
                                                        "tallaBotas": 44,
                                                        "tallaCamiseta": "S",
                                                        "tallaCalzonas": "S",
                                                        "tallaChandal": "M",
                                                        "foto": "hola.png",
                                                        "getDtoPack": {
                                                            "nombre": "Pack Básico",
                                                            "descripcion": "Incluye 5 entrenamientos",
                                                            "precio": 200.0
                                                        }
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
    @PutMapping("/edit/admin/{username}")
    public GetArbitroAdminEDto updateUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(
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
                                        "telefono": "654 832 999",
                                        "tallaBotas": 44,
                                        "tallaCamiseta": "S",
                                        "tallaCalzonas": "S",
                                        "tallaChandal": "M",
                                        "foto": "hola.png",
                                        "categoria": "OFICIAL",
                                        "pack": {
                                            "nombre": "Pack Básico",
                                            "descripcion": "Incluye 5 entrenamientos",
                                            "precio": 200
                                        }
                                    }
                            """
                    ))) @PathVariable String username, @RequestBody @Valid EditArbitroAdminEDto editArbitroAdminEDto) {

        Arbitro updateArbitro = arbitroService.editUser(username, editArbitroAdminEDto);
        return GetArbitroAdminEDto.of(updateArbitro);
    }


    @Operation(summary = "Busca árbitros según criterios de búsqueda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de árbitros encontrados según los criterios de búsqueda",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetSearchArbitroDto.class)),
                            examples = {@ExampleObject(
                                    value =
                                        """
                                                [
                                                    {
                                                        "nombre": "María",
                                                        "primerApellido": "Hernández",
                                                        "segundoApellido": "Díaz",
                                                        "username": "mhernandez",
                                                        "email": "mhernandez@example.com",
                                                        "telefono": "600222333",
                                                        "password": "{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy",
                                                        "fechaNacimiento": "1997-01-01",
                                                        "edad": 28,
                                                        "categoria": "PRIMERA",
                                                        "fechaInscripcion": "2020-01-01",
                                                        "tallaBotas": 41,
                                                        "tallaCamiseta": "M",
                                                        "tallaCalzonas": "M",
                                                        "tallaChandal": "M",
                                                        "foto": "https://www.example.com/foto4.jpg",
                                                        "asistencias": null,
                                                        "pack": null
                                                    },
                                                    {
                                                        "nombre": "Pedro",
                                                        "primerApellido": "Torres",
                                                        "segundoApellido": "Gómez",
                                                        "username": "ptorres",
                                                        "email": "ptorres@example.com",
                                                        "telefono": "600333444",
                                                        "password": "{bcrypt}$2a$12$cIYPIMkHiCd.qakhyfrCXeqltzKwoTSRAK9I/aFCo2z4qyNX1bVwy",
                                                        "fechaNacimiento": "1993-01-01",
                                                        "edad": 32,
                                                        "categoria": "SEGUNDA",
                                                        "fechaInscripcion": "2020-01-01",
                                                        "tallaBotas": 43,
                                                        "tallaCamiseta": "L",
                                                        "tallaCalzonas": "L",
                                                        "tallaChandal": "L",
                                                        "foto": "https://www.example.com/foto5.jpg",
                                                        "asistencias": null,
                                                        "pack": null
                                                    }
                                                ]
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Los parámetros de búsqueda no son válidos",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado árbitros con los criterios dados",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content)
    })
    @GetMapping("/search/")
    public List<GetSearchArbitroDto> buscar(@RequestParam(value="search", required = false) String search) {
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

        return arbitroService.buscarArbitros(params);

    }

}
