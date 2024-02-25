package com.doremi.booking.repository;
import com.doremi.booking.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ImagenRepository extends JpaRepository <Imagen, Long> {
}
