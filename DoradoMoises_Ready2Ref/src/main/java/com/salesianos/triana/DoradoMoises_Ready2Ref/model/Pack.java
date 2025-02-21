package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Pack {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;
    private String descripcion;
    private double precio;

    @OneToMany(mappedBy = "pack")
    private List<Recibo> listaArbitros;

}
