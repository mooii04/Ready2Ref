package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
public class Entrenador extends User{

    @OneToMany(mappedBy = "entrenador",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Entrenamiento> entrenamientos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "entrenador_mensaje",
            joinColumns = @JoinColumn(name = "entrenador_id"),
            inverseJoinColumns = @JoinColumn(name = "mensaje_id")
    )
    @ToString.Exclude
    private Set<Mensaje> mensajesEntrenador = new HashSet<>();

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
