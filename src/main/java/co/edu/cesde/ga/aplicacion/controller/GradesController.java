package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.models.Grades;
import co.edu.cesde.ga.aplicacion.service.GradesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@CrossOrigin(origins = "*")
public class GradesController {

    private final GradesService gradesService;

    public GradesController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    // GET /api/grades -> lista todas las notas
    @GetMapping
    public ResponseEntity<List<Grades>> findAll() {
        return ResponseEntity.ok(gradesService.findAll());
    }

    // GET /api/grades/{id} -> busca una nota por su id
    @GetMapping("/{id}")
    public ResponseEntity<Grades> findById(@PathVariable Long id) {
        Grades grade = gradesService.findById(id);
        if (grade == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grade);
    }

    // POST /api/grades -> crea una nueva nota
    @PostMapping
    public ResponseEntity<Grades> save(@RequestBody Grades grade) {
        Grades created = gradesService.create(grade);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/grades/{id} -> actualiza una nota existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Grades grade) {
        grade.setGradeId(id);
        boolean updated = gradesService.update(grade);
        return updated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // DELETE /api/grades/{id} -> elimina una nota por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean deleted = gradesService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
