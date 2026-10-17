package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.application.service.TeacherService;
import co.edu.cesde.ga.aplicacion.controller.dto.CreateTeacherDto;
import co.edu.cesde.ga.aplicacion.controller.dto.UpdateTeacherDto;
import co.edu.cesde.ga.aplicacion.controller.dto.response.TeacherResponseDto;
import co.edu.cesde.ga.aplicacion.domain.Teachers;
import co.edu.cesde.ga.aplicacion.domain.exceptions.TeacherNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponseDto>> findAll() {
        List<TeacherResponseDto> teachers = teacherService.findAll()
                .stream()
                .map(TeacherResponseDto::from)
                .toList();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> findById(@PathVariable Long id) {
        Teachers teacher = teacherService.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
        return ResponseEntity.ok(TeacherResponseDto.from(teacher));
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDto> save(@Valid @RequestBody CreateTeacherDto dto) {
        Teachers teacher = teacherService.create(dto.toTeacher(), dto.userId());
        return ResponseEntity.status(HttpStatus.CREATED).body(TeacherResponseDto.from(teacher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateTeacherDto dto) {
        Teachers updated = teacherService.update(dto.toTeacher(id), dto.userId())
                .orElseThrow(() -> new TeacherNotFoundException(id));
        return ResponseEntity.ok(TeacherResponseDto.from(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
