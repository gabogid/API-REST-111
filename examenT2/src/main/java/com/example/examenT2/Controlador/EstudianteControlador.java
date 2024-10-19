package com.example.examenT2.Controlador;

import com.example.examenT2.Entidad.Curso;
import com.example.examenT2.Entidad.Estudiante;
import com.example.examenT2.JsendResponse;
import com.example.examenT2.Repositorio.CursoRepositorio;
import com.example.examenT2.Repositorio.EstudianteRepositorio;
import com.example.examenT2.Servicio.EstudianteServicio;
import org.apache.coyote.Response;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteControlador {

    private final EstudianteServicio estudianteServicio;

    public EstudianteControlador(EstudianteServicio estudianteServicio) {
        this.estudianteServicio = estudianteServicio;
    }

    @PostMapping("/crear")
    public ResponseEntity<JsendResponse<Estudiante>> crearEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteServicio.crearEstudiante(estudiante);
        JsendResponse<Estudiante> response = new JsendResponse<>("success", nuevoEstudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/todos")
    public ResponseEntity<JsendResponse<List<Estudiante>>> obtenerTodos() {
        List<Estudiante> estudiantes = estudianteServicio.obtenerTodos();
        JsendResponse<List<Estudiante>> response = new JsendResponse<>("success", estudiantes);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JsendResponse<Estudiante>> actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        Estudiante actualizado = estudianteServicio.actualizarEstudiante(id, estudiante);
        JsendResponse<Estudiante> response = new JsendResponse<>("success", actualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsendResponse<Void>> eliminarEstudiante(@PathVariable Long id) {
        estudianteServicio.eliminarEstudiante(id);
        JsendResponse<Void> response = new JsendResponse<>("success", "Estudiante eliminado");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/inscribir/{cursoId}")
    public ResponseEntity<JsendResponse<Estudiante>> inscribirCurso(@PathVariable Long id, @PathVariable Long cursoId) {
        Estudiante actualizado = estudianteServicio.inscribirCurso(id, cursoId);
        JsendResponse<Estudiante> response = new JsendResponse<>("success", actualizado);
        return ResponseEntity.ok(response);
    }
}
