package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
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
public class Entrenamiento {

    @Id
    @GeneratedValue
    private UUID idEntrenamiento;

    private LocalDate fecha;

    @OneToMany(mappedBy = "entrenamiento",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Asistencia> asistencias = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "entrenador_id", foreignKey = @ForeignKey(name = "fk_entrenador_entrenamiento"))
    private Entrenador entrenador;

    // Métodos helpers con entrenador
    public void addEntrenador(Entrenador e) {
        this.entrenador = e;
        e.getEntrenamientos().add(this);
    }

    public void removeEntrenador(Entrenador e) {
        this.entrenador = null;
        e.getEntrenamientos().remove(this);
    }
}
