package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.models.Subjects;
import co.edu.cesde.ga.aplicacion.service.SubjectsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin(origins = "*")
public class SubjectsController {

    private final SubjectsService subjectsService;

    public SubjectsController(SubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    // GET /api/subjects -> lista todas las materias
    @GetMapping
    public ResponseEntity<List<Subjects>> findAll() {
        return ResponseEntity.ok(subjectsService.findAll());
    }

    // GET /api/subjects/{id} -> busca una materia por su id
    @GetMapping("/{id}")
    public ResponseEntity<Subjects> findById(@PathVariable Long id) {
        Subjects subject = subjectsService.findById(id);
        if (subject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subject);
    }

    // POST /api/subjects -> crea una nueva materia
    @PostMapping
    public ResponseEntity<Subjects> save(@RequestBody Subjects subject) {
        Subjects created = subjectsService.create(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/subjects/{id} -> actualiza una materia existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Subjects subject) {
        subject.setSubjectId(id);
        boolean updated = subjectsService.update(subject);
        return updated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // DELETE /api/subjects/{id} -> elimina una materia por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean deleted = subjectsService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
