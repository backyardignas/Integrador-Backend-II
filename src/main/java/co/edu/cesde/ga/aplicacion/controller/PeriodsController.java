package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.application.service.PeriodsService;
import co.edu.cesde.ga.aplicacion.controller.dto.CreatePeriodDto;
import co.edu.cesde.ga.aplicacion.controller.dto.UpdatePeriodDto;
import co.edu.cesde.ga.aplicacion.controller.dto.response.PeriodResponseDto;
import co.edu.cesde.ga.aplicacion.domain.Periods;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periods")
public class PeriodsController {

    private final PeriodsService periodsService;

    public PeriodsController(PeriodsService periodsService) {
        this.periodsService = periodsService;
    }

    @GetMapping
    public ResponseEntity<List<PeriodResponseDto>> findAll() {
        List<PeriodResponseDto> periods = periodsService.findAll()
                .stream()
                .map(PeriodResponseDto::from)
                .toList();
        return ResponseEntity.ok(periods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodResponseDto> findById(@PathVariable Long id) {
        Periods period = periodsService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("un periodo", id));
        return ResponseEntity.ok(PeriodResponseDto.from(period));
    }

    @PostMapping
    public ResponseEntity<PeriodResponseDto> save(@Valid @RequestBody CreatePeriodDto dto) {
        Periods period = periodsService.create(dto.toPeriod());
        return ResponseEntity.status(HttpStatus.CREATED).body(PeriodResponseDto.from(period));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeriodResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdatePeriodDto dto) {
        Periods updated = periodsService.update(dto.toPeriod(id))
                .orElseThrow(() -> new ResourceNotFoundException("un periodo", id));
        return ResponseEntity.ok(PeriodResponseDto.from(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        periodsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
