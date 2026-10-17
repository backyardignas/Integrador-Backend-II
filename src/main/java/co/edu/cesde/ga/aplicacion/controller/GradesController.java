package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.application.service.GradesService;
import co.edu.cesde.ga.aplicacion.controller.dto.CreateGradeDto;
import co.edu.cesde.ga.aplicacion.controller.dto.UpdateGradeDto;
import co.edu.cesde.ga.aplicacion.controller.dto.response.GradeResponseDto;
import co.edu.cesde.ga.aplicacion.domain.Grades;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradesController {

    private final GradesService gradesService;

    public GradesController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @GetMapping
    public ResponseEntity<List<GradeResponseDto>> findAll() {
        List<GradeResponseDto> grades = gradesService.findAll()
                .stream()
                .map(GradeResponseDto::from)
                .toList();
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeResponseDto> findById(@PathVariable Long id) {
        Grades grade = gradesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("una nota", id));
        return ResponseEntity.ok(GradeResponseDto.from(grade));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeResponseDto>> findByStudent(@PathVariable Long studentId) {
        List<GradeResponseDto> grades = gradesService.findByStudent(studentId)
                .stream()
                .map(GradeResponseDto::from)
                .toList();
        return ResponseEntity.ok(grades);
    }

    @PostMapping
    public ResponseEntity<GradeResponseDto> save(@Valid @RequestBody CreateGradeDto dto) {
        Grades grade = gradesService.create(dto.studentId(), dto.groupSubjectId(), dto.finalScore(), dto.observation());
        return ResponseEntity.status(HttpStatus.CREATED).body(GradeResponseDto.from(grade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateGradeDto dto) {
        Grades updated = gradesService.update(id, dto.finalScore(), dto.observation())
                .orElseThrow(() -> new ResourceNotFoundException("una nota", id));
        return ResponseEntity.ok(GradeResponseDto.from(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        gradesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
