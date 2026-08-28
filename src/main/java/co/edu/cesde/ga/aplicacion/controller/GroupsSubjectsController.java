package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.models.GroupsSubjects;
import co.edu.cesde.ga.aplicacion.service.GroupSubjectsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-subjects")
@CrossOrigin(origins = "*")
public class GroupsSubjectsController {

    private final GroupSubjectsService groupSubjectsService;

    public GroupsSubjectsController(GroupSubjectsService groupSubjectsService) {
        this.groupSubjectsService = groupSubjectsService;
    }

    // GET /api/group-subjects -> lista todas las asignaciones grupo-materia
    @GetMapping
    public ResponseEntity<List<GroupsSubjects>> findAll() {
        return ResponseEntity.ok(groupSubjectsService.getAllAssignments());
    }

    // GET /api/group-subjects/{id} -> busca una asignación por su id
    @GetMapping("/{id}")
    public ResponseEntity<GroupsSubjects> findById(@PathVariable Long id) {
        GroupsSubjects assignment = groupSubjectsService.getAssignmentById(id);
        if (assignment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assignment);
    }

    // GET /api/group-subjects/group/{groupId} -> materias asignadas a un grupo
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<GroupsSubjects>> findByGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(groupSubjectsService.getSubjectsByGroup(groupId));
    }

    // POST /api/group-subjects -> asigna una materia a un grupo
    // (assignSubjectToGroup es void: se devuelve el objeto recibido)
    @PostMapping
    public ResponseEntity<GroupsSubjects> save(@RequestBody GroupsSubjects groupSubject) {
        groupSubjectsService.assignSubjectToGroup(groupSubject);
        return ResponseEntity.status(HttpStatus.CREATED).body(groupSubject);
    }

    // DELETE /api/group-subjects/{id} -> quita una materia de un grupo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        groupSubjectsService.removeAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
