package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.file.FileResponse;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.FileMetadata;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.MensajeService;
import com.salesianos.triana.DoradoMoises_Ready2Ref.util.MimeTypeDetector;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.files.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Tag(name = "Gestión de archivos", description = "Controlador para la subida y gestión de archivos")
public class FileController {

    private final StorageService storageService;
    private final MimeTypeDetector mimeTypeDetector;
    private final MensajeService mensajeService;

    @Operation(summary = "Subir un archivo", description = "Permite a un usuario subir un archivo al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Archivo subido correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FileResponse.class),
                            examples = { @ExampleObject(
                                    value = """
                                            {
                                                "id": "Semana 25_ 16 - 22  diciembre 2024_930132.pdf",
                                                "name": "Semana 25_ 16 - 22  diciembre 2024_930132.pdf",
                                                "uri": "http://localhost:8080/download/Semana%2025_%2016%20-%2022%20%20diciembre%202024_930132.pdf",
                                                "type": "application/pdf",
                                                "size": 651557
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "400", description = "Error en la subida del archivo", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content),
            @ApiResponse(responseCode = "403", description = "No tienes permisos para subir archivos", content = @Content)
    })
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestPart("file") MultipartFile file) {

        FileMetadata fileMetadata = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileMetadata.getId())
                .toUriString();

        fileMetadata.setURL(uri);

        FileResponse response = FileResponse.builder()
                .id(fileMetadata.getId())
                .name(fileMetadata.getFilename())
                .size(file.getSize())
                .type(file.getContentType())
                .uri(uri)
                .build();

        mensajeService.enviarMensajeEntrenoSubido(fileMetadata);

        return ResponseEntity.created(URI.create(response.uri())).body(response);
    }

    @Operation(summary = "Descargar un archivo por ID", description = "Obtiene un archivo almacenado en el servidor según su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Archivo descargado correctamente",
                    content = @Content(mediaType = "application/octet-stream",
                            schema = @Schema(type = "string", format = "binary"),
                            examples = @ExampleObject(value = "Archivo en formato binario"))),
            @ApiResponse(responseCode = "400", description = "El ID del archivo no es válido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Archivo no encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "No tienes permisos para acceder a este recurso", content = @Content),
            @ApiResponse(responseCode = "403", description = "No tienes permisos para acceder a este recurso", content = @Content)
    })
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable String id) {
        Resource resource = storageService.loadAsResource(id);

        String mimeType = mimeTypeDetector.getMimeType(resource);
        String filename = resource.getFilename();

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", mimeType)
                .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

}
