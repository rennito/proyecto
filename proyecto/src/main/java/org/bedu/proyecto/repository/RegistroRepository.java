package org.bedu.proyecto.repository;

import org.bedu.proyecto.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

    // Encuentra registros por nombre
    List<Registro> findByNombre(String nombre);

    // Encuentra registros por email
    List<Registro> findByEmail(String email);

    // Encuentra registros por estado
    List<Registro> findByEstado(String estado);

    // Encuentra registros por fecha de inscripción
    List<Registro> findByFechaInscripcion(LocalDate fechaInscripcion);

    // Encuentra registros que contengan un texto en el nombre
    List<Registro> findByNombreContaining(String nombre);

      // Encuentra registro por numeroId
    Optional<Registro> findByNumeroId(String numeroId);
    

    // Actualiza la dirección de un registro por su ID
    @Transactional
    @Modifying
    @Query("UPDATE Registro r SET r.direccion = :direccion WHERE r.id = :id")
    void updateDireccionById(@Param("id") Long id, @Param("direccion") String direccion);

    // Elimina un registro por ID
    @Transactional
    @Modifying
    @Query("DELETE FROM Registro r WHERE r.id = :id")
    void deleteById(@Param("id") Long id);

    @Query("SELECT COALESCE(MAX(CAST(r.numeroId AS LONG)), 0) FROM Registro r")
    Long findMaxId();
}
