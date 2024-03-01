package com.doremi.booking.repository;

import com.doremi.booking.entity.Accion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccionRepository extends JpaRepository<Accion, Long>{

    List<Accion> findByEstado(Boolean estado);

}
