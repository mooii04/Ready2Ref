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
public class Recibo {

    @Id
    @GeneratedValue
    private UUID id;

    private double cantidad;
    private String concepto;
    private String fechaPago;
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "arbitro_id", nullable = false)
    private Arbitro arbitro;

    @ManyToOne
    @JoinColumn(name = "pack_id", nullable = false)
    private Pack pack;

}
