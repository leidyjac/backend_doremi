package com.doremi.booking.dto.entrada.reseva;

import com.doremi.booking.entity.Instrumento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ReservaEntradaDto {

   /* @NotNull(message = "El usuario no puede ser nulo")
   private Long usuarioId;*/
    @NotNull(message = "El instrumento no puede ser nulo")
   private Long instrumentoId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message= "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de inicio de la reserva")
    private LocalDate fechaInicial;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message= "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha final de la reserva")
    private LocalDate fechaFinal;
}
