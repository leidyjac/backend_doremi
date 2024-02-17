package com.doremi.booking.dto.salida.instrumento;

public class InstrumentoSalidaDto {
    private Long id;
    private String nombre;
    private String tipo;
    private String descripcion;
    private double precio;
    private String imagen;

    public InstrumentoSalidaDto() {

    }

    public InstrumentoSalidaDto(Long id, String nombre, String tipo, String descripcion, double precio, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Tipo: " + tipo + " - Descripción: " + descripcion + " - Precio:: " + precio + " -Imagen: " + imagen;
    }
}
