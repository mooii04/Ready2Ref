package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    private Long idArbitro;
    private Long idEntrenamiento;

}
