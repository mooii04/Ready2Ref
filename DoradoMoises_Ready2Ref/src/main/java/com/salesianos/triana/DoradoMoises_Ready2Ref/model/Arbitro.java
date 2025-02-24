package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@SuperBuilder
public class Arbitro extends User{

    private LocalDate fechaNacimiento;
    private int edad;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private LocalDate fechaInscripcion;
    private int tallaBotas;

    @Enumerated(EnumType.STRING)
    private Talla tallaCamiseta;

    @Enumerated(EnumType.STRING)
    private Talla tallaCalzonas;

    @Enumerated(EnumType.STRING)
    private Talla tallaChandal;

    private String foto;

    @OneToMany(mappedBy = "arbitro",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Asistencia> asistencias = new ArrayList<>();

    @OneToOne
    private Recibo recibos;

    @ManyToOne
    @JoinColumn(name = "arbitro_id", foreignKey = @ForeignKey(name = "fk_arbitro_asistencia"))
    private Pack pack;

    @ManyToMany
    @JoinTable(
            name = "arbitro_mensaje",
            joinColumns = @JoinColumn(name = "arbitro_id"),
            inverseJoinColumns = @JoinColumn(name = "mensaje_id")
    )
    private Set<Mensaje> mensajes = new HashSet<>();

}