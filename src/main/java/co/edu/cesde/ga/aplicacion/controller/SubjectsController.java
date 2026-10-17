package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.application.service.SubjectsService;
import co.edu.cesde.ga.aplicacion.controller.dto.CreateSubjectDto;
import co.edu.cesde.ga.aplicacion.controller.dto.UpdateSubjectDto;
import co.edu.cesde.ga.aplicacion.controller.dto.response.SubjectResponseDto;
import co.edu.cesde.ga.aplicacion.domain.Subjects;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectsController {

    private final SubjectsService subjectsService;

    public SubjectsController(SubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponseDto>> findAll() {
        List<SubjectResponseDto> subjects = subjectsService.findAll()
                .stream()
                .map(SubjectResponseDto::from)
                .toList();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> findById(@PathVariable Long id) {
        Subjects subject = subjectsService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("una materia", id));
        return ResponseEntity.ok(SubjectResponseDto.from(subject));
    }

    @PostMapping
    public ResponseEntity<SubjectResponseDto> save(@Valid @RequestBody CreateSubjectDto dto) {
        Subjects subject = subjectsService.create(dto.toSubject(), dto.programId());
        return ResponseEntity.status(HttpStatus.CREATED).body(SubjectResponseDto.from(subject));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateSubjectDto dto) {
        Subjects updated = subjectsService.update(dto.toSubject(id), dto.programId())
                .orElseThrow(() -> new ResourceNotFoundException("una materia", id));
        return ResponseEntity.ok(SubjectResponseDto.from(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        subjectsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
