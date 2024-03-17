package com.doremi.booking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "categoria_id")
    private Long categoria_id;
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    private String imagen;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Instrumento> instrumentos;

    public Categoria(String nombre, String descripcion, String imagen, List<Instrumento> instrumentos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.instrumentos = instrumentos;
    }
}
