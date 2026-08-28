package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.models.Roles;
import co.edu.cesde.ga.aplicacion.service.RolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    // GET /api/roles -> lista todos los roles
    @GetMapping
    public ResponseEntity<List<Roles>> findAll() {
        return ResponseEntity.ok(rolesService.getAllRoles());
    }

    // GET /api/roles/{id} -> busca un rol por su id
    @GetMapping("/{id}")
    public ResponseEntity<Roles> findById(@PathVariable Long id) {
        Roles role = rolesService.getRoleById(id);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }

    // GET /api/roles/name/{name} -> busca un rol por su nombre
    @GetMapping("/name/{name}")
    public ResponseEntity<Roles> findByName(@PathVariable String name) {
        Roles role = rolesService.getRoleByName(name);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }

    // POST /api/roles -> crea un nuevo rol (createRole es void: se devuelve el objeto recibido)
    @PostMapping
    public ResponseEntity<Roles> save(@RequestBody Roles role) {
        rolesService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    // DELETE /api/roles/{id} -> elimina un rol por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        rolesService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
