package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.models.Programs;
import co.edu.cesde.ga.aplicacion.service.ProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@CrossOrigin(origins = "*")
public class ProgramsController {

    private final ProgramService programService;

    public ProgramsController(ProgramService programService) {
        this.programService = programService;
    }

    // GET /api/programs -> lista todos los programas académicos
    @GetMapping
    public ResponseEntity<List<Programs>> findAll() {
        return ResponseEntity.ok(programService.getAllPrograms());
    }

    // POST /api/programs -> crea un nuevo programa académico
    @PostMapping
    public ResponseEntity<Programs> save(@RequestBody Programs program) {
        Programs created = programService.createProgram(program);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ProgramService solo expone createProgram/getAllPrograms; si necesitas
    // GET por id, PUT o DELETE, agrega esos métodos a la interfaz y su
    // implementación (ProgramServiceImpl) siguiendo el patrón de StudentService.
}
