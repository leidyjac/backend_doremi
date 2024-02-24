package com.doremi.booking.repository;

import com.doremi.booking.entity.Accion;
import com.doremi.booking.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
}
