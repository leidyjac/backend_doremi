package com.doremi.booking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "IMAGEN")

public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    private Long id;

    private String titulo;
    private String url;

    @ManyToOne
    @JoinColumn(name = "instrumento_id")
    private Instrumento instrumento;

    public Imagen(String titulo, String url, Instrumento instrumento) {
        this.titulo = titulo;
        this.url = url;
        this.instrumento = instrumento;
    }
}
