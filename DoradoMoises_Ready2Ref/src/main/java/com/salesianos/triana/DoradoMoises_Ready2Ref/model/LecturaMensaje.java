package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LecturaMensaje {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User usuario;

    @ManyToOne
    private Mensaje mensaje;

    private boolean leido = false;

    private LocalDate fechaLectura;

    public boolean isLeido() {
        return leido;
    }

    public void marcarComoLeido() {
        this.leido = true;
        this.fechaLectura = LocalDate.now();
    }
}
