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

    private boolean asistio;

    @ManyToOne
    @JoinColumn(name = "arbitro_id", foreignKey = @ForeignKey(name = "fk_arbitro_asistencia"))
    private Arbitro arbitro;

    @ManyToOne
    @JoinColumn(name = "entrenamiento_id", foreignKey = @ForeignKey(name = "fk_entrenamiento_asistencia"))
    private Entrenamiento entrenamiento;

}
