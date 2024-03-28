package com.doremi.booking.listeners;

import com.doremi.booking.entity.Reserva;
import jakarta.persistence.PrePersist;

import java.time.LocalDate;

public class ReservaListener {

    @PrePersist
    public void antesDePersistir(Reserva reserva) {
        reserva.setFechaReserva(LocalDate.now());
    }
}
