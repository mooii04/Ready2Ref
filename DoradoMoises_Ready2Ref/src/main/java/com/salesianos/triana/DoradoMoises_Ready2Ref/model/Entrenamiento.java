package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Entrenamiento {

    @Id
    @GeneratedValue
    private UUID idEntrenamiento;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "entrenador_id", nullable = false)
    private Entrenador entrenador;

    @OneToMany(mappedBy = "entrenamiento")
    private List<Asistencia> asistencias;

}
