package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Asistencia {

    @Id @GeneratedValue
    private UUID idAsistencia;

    @ManyToOne
    @JoinColumn(name = "arbitro_id", nullable = false)
    private Arbitro arbitro;

    @ManyToOne
    @JoinColumn(name = "entrenamiento_id", nullable = false)
    private Entrenamiento entrenamiento;

}
