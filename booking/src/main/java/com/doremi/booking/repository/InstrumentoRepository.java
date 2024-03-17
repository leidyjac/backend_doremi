package com.doremi.booking.repository;

import com.doremi.booking.entity.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentoRepository extends JpaRepository<Instrumento, Long> {
    Instrumento findByNombre(String nombre);
    List<Instrumento> findByNombreContainingIgnoreCase(String nombre);
}


