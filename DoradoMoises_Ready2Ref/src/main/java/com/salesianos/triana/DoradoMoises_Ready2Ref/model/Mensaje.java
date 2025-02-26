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

    // Métodos helpers para gestionar la relación bidireccional
    public void addArbitro(Arbitro arbitro) {
        arbitros.add(arbitro);
        arbitro.getMensajesArbitro().add(this);
    }

    public void removeArbitro(Arbitro arbitro) {
        arbitros.remove(arbitro);
        arbitro.getMensajesArbitro().remove(this);
    }

    public void addEntrenador(Set<Entrenador> entrenadores) {
        entrenadores.addAll(entrenadores);
        for (Entrenador entrenador : entrenadores) {
            entrenador.getMensajesEntrenador().add(this);
        }
    }

    public void removeEntrenador(Entrenador entrenador) {
        entrenadores.remove(entrenador);
        entrenador.getMensajesEntrenador().remove(this);
    }

}