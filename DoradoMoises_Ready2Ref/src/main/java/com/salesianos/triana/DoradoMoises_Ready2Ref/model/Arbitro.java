package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@SuperBuilder
public class Arbitro extends User{

    private LocalDate fechaNacimiento;
    private int edad;
    private Categoria categoria;
    private LocalDate fechaInscripcion;
    private int tallaBotas;
    private Talla tallaCamiseta;
    private Talla tallaCalzonas;
    private Talla tallaChandal;
    private String foto;

}
