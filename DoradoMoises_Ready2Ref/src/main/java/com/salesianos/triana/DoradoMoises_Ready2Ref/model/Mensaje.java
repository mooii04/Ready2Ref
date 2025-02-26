package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String asunto;
    private String contenido;
    private LocalDate fechaEnvio;
    private boolean leido;

    @ManyToMany(mappedBy = "mensajesArbitro")
    private Set<Arbitro> arbitros = new HashSet<>();

    @ManyToMany(mappedBy = "mensajesEntrenador")
    private Set<Entrenador> entrenadores = new HashSet<>();

}