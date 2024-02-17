package com.doremi.booking.dto.entrada.instrumento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstrumentoEntradaDto {
    @NotNull(message = "El nombre del instrumento no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del instrumento")
    private String nombre;
    @NotNull(message = "El tipo de instrumento no puede ser nulo")
    @NotBlank(message = "Debe especificarse el tipo de instrumento")
    private String tipo;

    @NotNull(message = "La descripción del instrumento no puede ser nulo")
    @NotBlank(message = "Debe especificarse una descripción del instrumento")
    private String descripcion;

    private double precio;

    private String imagen;

    public InstrumentoEntradaDto() {
    }

    public InstrumentoEntradaDto(String nombre, String tipo, String descripcion, double precio, String imagen) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
