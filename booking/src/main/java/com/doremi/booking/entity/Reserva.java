package com.doremi.booking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "RESERVAS")

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id")
    private Long idReserva;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "instrumento_id")
    private Instrumento instrumento;

    private LocalDate fechaInicial;

    private LocalDate fechaFinal;

    //pendiente incluir campos no obligatorios

    public Reserva(User usuario, Instrumento instrumento, LocalDate fechaInicial, LocalDate fechaFinal) {
        this.usuario = usuario;
        this.instrumento = instrumento;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }
}

