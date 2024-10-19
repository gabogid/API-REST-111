package com.example.examenT2.Repositorio;

import com.example.examenT2.Entidad.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findByApellido(String apellido);

    @Query("SELECT e FROM Estudiante e JOIN e.cursos c WHERE c.id = :cursoId")
    List<Estudiante> findByCursoId(@Param("cursoId") Long cursoId);
}
