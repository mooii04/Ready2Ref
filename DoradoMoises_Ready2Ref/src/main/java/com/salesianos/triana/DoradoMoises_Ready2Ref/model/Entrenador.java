package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@SuperBuilder
public class Entrenador extends User{

    @OneToMany(mappedBy = "entrenador",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Entrenamiento> entrenamientos = new ArrayList<>();

    // Métodos helpers para gestionar la relación bidireccional
    public void addEntrenamiento(Entrenamiento entrenamiento) {
        entrenamientos.add(entrenamiento);
        entrenamiento.setEntrenador(this);
    }

    public void removeEntrenamiento(Entrenamiento entrenamiento) {
        entrenamientos.remove(entrenamiento);
        entrenamiento.setEntrenador(null);
    }

}
