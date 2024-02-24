package com.doremi.booking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "INSTRUMENTO")
public class Instrumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    private String nombre;
    private String tipo;
    private String descripcion;
    private double precioDia;

    @OneToMany(mappedBy = "instrumento", cascade = CascadeType.ALL)
    private List<Imagen> imagenes = new ArrayList<>();

    public Instrumento(String nombre, String tipo, String descripcion, double precioDia, List<Imagen> imagenes) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precioDia = precioDia;
        this.imagenes = imagenes;
    }
}