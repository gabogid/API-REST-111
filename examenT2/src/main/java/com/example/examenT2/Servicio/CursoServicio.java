package com.example.examenT2.Servicio;

import com.example.examenT2.Entidad.Curso;
import com.example.examenT2.Entidad.Estudiante;
import com.example.examenT2.Repositorio.CursoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServicio {
    private final CursoRepositorio cursoRepositorio;

    public CursoServicio(CursoRepositorio cursoRepositorio){
        this.cursoRepositorio = cursoRepositorio;
    }

    public Curso crearCurso(Curso curso){
        return cursoRepositorio.save(curso);
    }

    public List<Curso> obtenerTodos(){
        return cursoRepositorio.findAll();
    }
    public Curso actualizarCurso(Long id, Curso cursoActualizado){
        Curso curso = cursoRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        curso.setNombre(cursoActualizado.getNombre());
        curso.setDescripcion(cursoActualizado.getDescripcion());

        return cursoRepositorio.save(curso);
    }

    public void eliminarCurso(Long id){
        cursoRepositorio.deleteById(id);
    }

    public List<Estudiante> obtenerEstudiantesPorCurso(Long cursoId) {
        return estudianteRepositorio.findByCursoId(cursoId);
    }
}