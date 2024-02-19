package com.doremi.booking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "ACCION")
public class Accion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    private Long id;
    private String nombre;
    private Boolean estado;

    public Accion(String nombre, Boolean estado) {
        this.nombre = nombre;
        this.estado = estado;
    }
}


