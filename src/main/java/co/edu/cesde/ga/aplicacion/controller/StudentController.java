package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.application.service.StudentService;
import co.edu.cesde.ga.aplicacion.controller.dto.CreateStudentDto;
import co.edu.cesde.ga.aplicacion.controller.dto.UpdateStudentDto;
import co.edu.cesde.ga.aplicacion.controller.dto.response.StudentResponseDto;
import co.edu.cesde.ga.aplicacion.domain.Students;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.StudentNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> findAll() {
        List<StudentResponseDto> students = studentService.findAll()
                .stream()
                .map(StudentResponseDto::from)
                .toList();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> findById(@PathVariable Long id) {
        Students student = studentService.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return ResponseEntity.ok(StudentResponseDto.from(student));
    }

    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<StudentResponseDto> findByDocumentNumber(@PathVariable String documentNumber) {
        Students student = studentService.findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró un estudiante con el número de documento: " + documentNumber));
        return ResponseEntity.ok(StudentResponseDto.from(student));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> save(@Valid @RequestBody CreateStudentDto dto) {
        Students student = studentService.create(dto.toStudent(), dto.userId());
        return ResponseEntity.status(HttpStatus.CREATED).body(StudentResponseDto.from(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateStudentDto dto) {
        Students updated = studentService.update(dto.toStudent(id), dto.userId())
                .orElseThrow(() -> new StudentNotFoundException(id));
        return ResponseEntity.ok(StudentResponseDto.from(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
