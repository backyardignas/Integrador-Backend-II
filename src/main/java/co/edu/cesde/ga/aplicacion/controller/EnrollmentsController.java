package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.models.Enrollments;
import co.edu.cesde.ga.aplicacion.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "*")
public class EnrollmentsController {

    private final EnrollmentService enrollmentService;

    public EnrollmentsController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // GET /api/enrollments -> lista todas las matrículas
    @GetMapping
    public ResponseEntity<List<Enrollments>> findAll() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    // GET /api/enrollments/{id} -> busca una matrícula por su id
    @GetMapping("/{id}")
    public ResponseEntity<Enrollments> findById(@PathVariable Long id) {
        Enrollments enrollment = enrollmentService.getEnrollmentById(id);
        if (enrollment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enrollment);
    }

    // GET /api/enrollments/student/{studentId} -> matrículas de un estudiante
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollments>> findByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(studentId));
    }

    // POST /api/enrollments -> crea una matrícula (enrollStudent es void: se devuelve el objeto recibido)
    @PostMapping
    public ResponseEntity<Enrollments> save(@RequestBody Enrollments enrollment) {
        enrollmentService.enrollStudent(enrollment);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
    }

    // DELETE /api/enrollments/{id} -> cancela una matrícula por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        enrollmentService.cancelEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
