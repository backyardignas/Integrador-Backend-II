package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.application.service.EnrollmentService;
import co.edu.cesde.ga.aplicacion.controller.dto.CreateEnrollmentDto;
import co.edu.cesde.ga.aplicacion.controller.dto.UpdateEnrollmentDto;
import co.edu.cesde.ga.aplicacion.controller.dto.response.EnrollmentResponseDto;
import co.edu.cesde.ga.aplicacion.domain.Enrollments;
import co.edu.cesde.ga.aplicacion.domain.exceptions.EnrollmentNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentsController {

    private final EnrollmentService enrollmentService;

    public EnrollmentsController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDto>> findAll() {
        List<EnrollmentResponseDto> enrollments = enrollmentService.findAll()
                .stream()
                .map(EnrollmentResponseDto::from)
                .toList();
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDto> findById(@PathVariable Long id) {
        Enrollments enrollment = enrollmentService.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
        return ResponseEntity.ok(EnrollmentResponseDto.from(enrollment));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDto>> findByStudent(@PathVariable Long studentId) {
        List<EnrollmentResponseDto> enrollments = enrollmentService.findByStudent(studentId)
                .stream()
                .map(EnrollmentResponseDto::from)
                .toList();
        return ResponseEntity.ok(enrollments);
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDto> save(@Valid @RequestBody CreateEnrollmentDto dto) {
        Enrollments enrollment = enrollmentService.create(dto.studentId(), dto.subjectId(), dto.periodId(), dto.enrollmentDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(EnrollmentResponseDto.from(enrollment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDto> updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateEnrollmentDto dto) {
        Enrollments enrollment = enrollmentService.updateStatus(id, dto.toStatus());
        return ResponseEntity.ok(EnrollmentResponseDto.from(enrollment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        enrollmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
