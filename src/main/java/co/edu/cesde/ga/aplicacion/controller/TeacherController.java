package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.models.Teachers;
import co.edu.cesde.ga.aplicacion.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {

    // Inyección por constructor: evita @Autowired en el campo y permite crear el bean como inmutable/final.
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // GET /api/teachers -> lista todos los profesores
    @GetMapping
    public ResponseEntity<List<Teachers>> findAll() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    // GET /api/teachers/{id} -> busca un profesor por su id
    @GetMapping("/{id}")
    public ResponseEntity<Teachers> findById(@PathVariable Long id) {
        Teachers teacher = teacherService.findById(id);
        if (teacher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teacher);
    }

    // POST /api/teachers -> crea un nuevo profesor
    @PostMapping
    public ResponseEntity<Teachers> save(@RequestBody Teachers teacher) {
        Teachers created = teacherService.create(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/teachers/{id} -> actualiza un profesor existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Teachers teacher) {
        teacher.setTeacherId(id);
        boolean updated = teacherService.update(teacher);
        return updated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // DELETE /api/teachers/{id} -> elimina un profesor por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean deleted = teacherService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
