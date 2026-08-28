package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.models.Students;
import co.edu.cesde.ga.aplicacion.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    // Inyección por constructor: evita @Autowired en el campo y permite crear el bean como inmutable/final.
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /api/students -> lista todos los estudiantes
    @GetMapping
    public ResponseEntity<List<Students>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    // GET /api/students/{id} -> busca un estudiante por su id
    @GetMapping("/{id}")
    public ResponseEntity<Students> findById(@PathVariable Long id) {
        Students student = studentService.findById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    // POST /api/students -> crea un nuevo estudiante
    @PostMapping
    public ResponseEntity<Students> save(@RequestBody Students student) {
        Students created = studentService.create(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/students/{id} -> actualiza un estudiante existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Students student) {
        student.setStudentId(id);
        boolean updated = studentService.update(student);
        return updated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // DELETE /api/students/{id} -> elimina un estudiante por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean deleted = studentService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
