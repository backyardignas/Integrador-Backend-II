package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.models.Groups;
import co.edu.cesde.ga.aplicacion.service.GroupsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "*")
public class GroupsController {

    private final GroupsService groupsService;

    public GroupsController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    // GET /api/groups -> lista todos los grupos
    @GetMapping
    public ResponseEntity<List<Groups>> findAll() {
        return ResponseEntity.ok(groupsService.findAll());
    }

    // GET /api/groups/{id} -> busca un grupo por su id
    @GetMapping("/{id}")
    public ResponseEntity<Groups> findById(@PathVariable Long id) {
        Groups group = groupsService.findById(id);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(group);
    }

    // POST /api/groups -> crea un nuevo grupo
    @PostMapping
    public ResponseEntity<Groups> save(@RequestBody Groups group) {
        Groups created = groupsService.create(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/groups/{id} -> actualiza un grupo existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Groups group) {
        group.setGroupId(id);
        boolean updated = groupsService.update(group);
        return updated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // DELETE /api/groups/{id} -> elimina un grupo por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean deleted = groupsService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
