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
    @Column (name = "imagen_id")
    private Long imagen_id;

    private String titulo;
    private String url;

    //@ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "instrumento_id")
    private Long instrumento;

    public Imagen(String titulo, String url, Long instrumento) {
        this.titulo = titulo;
        this.url = url;
        this.instrumento = instrumento;
    }


}
