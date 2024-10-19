package com.example.examenT2.Servicio;

import com.example.examenT2.Entidad.Curso;
import com.example.examenT2.Entidad.Estudiante;
import com.example.examenT2.Repositorio.CursoRepositorio;
import com.example.examenT2.Repositorio.EstudianteRepositorio;
import com.example.examenT2.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServicio {

    private final EstudianteRepositorio estudianteRepositorio;
    private final CursoRepositorio cursoRepositorio;

    public EstudianteServicio(EstudianteRepositorio estudianteRepositorio, CursoRepositorio cursoRepositorio){
        this.estudianteRepositorio = estudianteRepositorio;
        this.cursoRepositorio = cursoRepositorio;
    }
    public Estudiante crearEstudiante(Estudiante estudiante) {
        return estudianteRepositorio.save(estudiante);
    }
    public List<Estudiante> obtenerTodos(){
        return estudianteRepositorio.findAll();
    }

    public Estudiante actualizarEstudiante(Long id, Estudiante estudianteActualizado){
        Estudiante estudiante = estudianteRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        estudiante.setNombre(estudianteActualizado.getNombre());
        estudiante.setApellido(estudianteActualizado.getApellido());
        estudiante.setEmail(estudianteActualizado.getEmail());
        estudiante.setDni(estudianteActualizado.getDni());

        return estudianteRepositorio.save(estudiante);
    }

    public void eliminarEstudiante(Long id) {
        estudianteRepositorio.deleteById(id);
    }

    public Estudiante inscribirCurso(Long estudianteId, Long cursoId) {
        Estudiante estudiante = estudianteRepositorio.findById(estudianteId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));
        Curso curso = cursoRepositorio.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        estudiante.getCursos().add(curso);
        return estudianteRepositorio.save(estudiante);
    }
}