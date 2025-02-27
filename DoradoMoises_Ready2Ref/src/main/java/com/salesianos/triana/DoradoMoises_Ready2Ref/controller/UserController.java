package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.ActivateAccountRequest;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.LoginRequest;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.UserResponse;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.GetArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.EditContraseniaDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.GetArbitroAdminEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.GetContraseniaDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.User;
import com.salesianos.triana.DoradoMoises_Ready2Ref.security.jwt.access.JwtService;
import com.salesianos.triana.DoradoMoises_Ready2Ref.security.jwt.refresh.RefreshToken;
import com.salesianos.triana.DoradoMoises_Ready2Ref.security.jwt.refresh.RefreshTokenRequest;
import com.salesianos.triana.DoradoMoises_Ready2Ref.security.jwt.refresh.RefreshTokenService;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Operation(summary = "Registro de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Árbitro creado correctamente",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LoginRequest.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "30e373f4-fe21-4166-8238-8bb933aa9041",
                                                "username": "alopez@example.com",
                                                "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzMGUzNzNmNC1mZTIxLTQxNjYtODIzOC04YmI5MzNhYTkwNDEiLCJpYXQiOjE3NDA2NTEyMzIsImV4cCI6MTc0MDY2MDIzMn0.3pOJkTA6S_DStfld3F34rb3LuYbbYp7k295W8TlglIQv3PPphmqFgwhOsICKGPKAP-fWGIqddS4dN5jMLw_KuQ",
                                                "refreshToken": "371c72fb-e808-4f4a-bf54-c32398beb227"
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
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del árbitro user", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Arbitro.class),
                    examples = @ExampleObject(value = """
                            {
                                "username": "alopez",
                                "password": "password123"
                            }
                            """))) @RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.username(),
                                loginRequest.password()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);

        // Generar el token de refresco
        RefreshToken refreshToken = refreshTokenService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.of(user, accessToken, refreshToken.getToken()));

    }

    @PostMapping("/auth/refresh/token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest req) {
        String token = req.refreshToken();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(refreshTokenService.refreshToken(token));

    }

    @PostMapping("/activate/account")
    public ResponseEntity<?> activateAccount(@RequestBody ActivateAccountRequest req) {
        String token = req.token();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.of(userService.activateAccount(token)));
    }

    @GetMapping("/me")
    public UserResponse me(@AuthenticationPrincipal User user) {
        return UserResponse.of(user);
    }

    @GetMapping("/me/admin")
    public User adminMe(@AuthenticationPrincipal User user) {
        return user;
    }

    @Operation(summary = "Edita tu contraseña")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado tu perfil",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetContraseniaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "oldPassword": "{bcrypt}$2a$10$/VtBWko855GJTt93YI//MumStNq5Gk6071JBZgT7oFq0llFM5xj26",
                                                "password": "{bcrypt}$2a$10$/VtBWko855GJTt93YI//MumStNq5Gk6071JBZgT7oFq0llFM5xj26",
                                                "verifyPassword": "{bcrypt}$2a$10$/VtBWko855GJTt93YI//MumStNq5Gk6071JBZgT7oFq0llFM5xj26"
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
    @PutMapping("/edit/contrasenia")
    public GetContraseniaDto updateUserPassword(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo del arbitro a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetContraseniaDto.class),
                    examples = @ExampleObject(value =
                            """
                                    {
                                        "oldPassword": "password123",
                                        "password": "12345678",
                                        "verifyPassword": "12345678"
                                    }
                            """
                    ))) @AuthenticationPrincipal User user, @RequestBody @Valid EditContraseniaDto editContraseniaDto) {
        User userUpdated = userService.updateUserPassword(user.getId(), editContraseniaDto);

        return GetContraseniaDto.of(userUpdated);
    }

    @Operation(summary = "Borra un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado el usuario",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class))
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el usuario",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permisos para acceder a este recurso",
                    content = @Content)
    })
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

}
