package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.application.service.ProgramService;
import co.edu.cesde.ga.aplicacion.controller.dto.CreateProgramDto;
import co.edu.cesde.ga.aplicacion.controller.dto.UpdateProgramDto;
import co.edu.cesde.ga.aplicacion.controller.dto.response.ProgramResponseDto;
import co.edu.cesde.ga.aplicacion.domain.Programs;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramsController {

    private final ProgramService programService;

    public ProgramsController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public ResponseEntity<List<ProgramResponseDto>> findAll() {
        List<ProgramResponseDto> programs = programService.findAll()
                .stream()
                .map(ProgramResponseDto::from)
                .toList();
        return ResponseEntity.ok(programs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramResponseDto> findById(@PathVariable Long id) {
        Programs program = programService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("un programa", id));
        return ResponseEntity.ok(ProgramResponseDto.from(program));
    }

    @PostMapping
    public ResponseEntity<ProgramResponseDto> save(@Valid @RequestBody CreateProgramDto dto) {
        Programs program = programService.create(dto.toProgram());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProgramResponseDto.from(program));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateProgramDto dto) {
        Programs updated = programService.update(dto.toProgram(id))
                .orElseThrow(() -> new ResourceNotFoundException("un programa", id));
        return ResponseEntity.ok(ProgramResponseDto.from(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        programService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
