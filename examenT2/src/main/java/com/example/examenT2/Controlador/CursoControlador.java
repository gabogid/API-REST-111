package com.example.examenT2.Controlador;

import com.example.examenT2.Entidad.Curso;
import com.example.examenT2.Entidad.Estudiante;
import com.example.examenT2.JsendResponse;
import com.example.examenT2.Repositorio.CursoRepositorio;
import com.example.examenT2.Repositorio.EstudianteRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoControlador {

    private final CursoRepositorio cursoRepositorio;

    public CursoControlador(CursoRepositorio cursoRepositorio){
        this.cursoRepositorio = cursoRepositorio;
    }
   @PostMapping("/crear")
   public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
       Curso nuevoCurso = cursoRepositorio.save(curso);
       return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
   }
    @GetMapping("/todos")
    public List<Curso> obtenerTodos() {
        return cursoRepositorio.findAll();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        return cursoRepositorio.findById(id)
                .map(c -> {
                    c.setNombre(curso.getNombre());
                    c.setDescripcion(curso.getDescripcion());
                    return ResponseEntity.ok(cursoRepositorio.save(c));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/estudiantes")
    public ResponseEntity<JsendResponse<List<Estudiante>>> obtenerEstudiantesPorCurso(@PathVariable Long id) {
        List<Estudiante> estudiantes = EstudianteRepositorio.findByCursoId(id);
        if (estudiantes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new JsendResponse<>("fail", "No hay estudiantes en este curso"));
        }
        JsendResponse<List<Estudiante>> response = new JsendResponse<>("success", estudiantes);
        return ResponseEntity.ok(response);
    }
}
