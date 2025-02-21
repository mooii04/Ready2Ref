package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@SuperBuilder
public class Entrenador extends User{

    @OneToMany(mappedBy = "entrenador")
    private List<Entrenamiento> listaEntrenamientos;

}
