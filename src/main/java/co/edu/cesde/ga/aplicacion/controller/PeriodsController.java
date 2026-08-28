package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.models.Periods;
import co.edu.cesde.ga.aplicacion.service.PeriodsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periods")
@CrossOrigin(origins = "*")
public class PeriodsController {

    private final PeriodsService periodsService;

    public PeriodsController(PeriodsService periodsService) {
        this.periodsService = periodsService;
    }

    // GET /api/periods -> lista todos los niveles/periodos académicos
    @GetMapping
    public ResponseEntity<List<Periods>> findAll() {
        return ResponseEntity.ok(periodsService.getAllPeriods());
    }

    // GET /api/periods/{id} -> busca un periodo por su id
    @GetMapping("/{id}")
    public ResponseEntity<Periods> findById(@PathVariable Long id) {
        Periods period = periodsService.getPeriodById(id);
        if (period == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(period);
    }

    // POST /api/periods -> crea un nuevo periodo (createPeriod es void: se devuelve el objeto recibido)
    @PostMapping
    public ResponseEntity<Periods> save(@RequestBody Periods period) {
        periodsService.createPeriod(period);
        return ResponseEntity.status(HttpStatus.CREATED).body(period);
    }

    // PUT /api/periods/{id} -> actualiza un periodo existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Periods period) {
        period.setPeriodId(id);
        periodsService.updatePeriod(period);
        return ResponseEntity.noContent().build();
    }

    // DELETE /api/periods/{id} -> elimina un periodo por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        periodsService.deletePeriod(id);
        return ResponseEntity.noContent().build();
    }
}
