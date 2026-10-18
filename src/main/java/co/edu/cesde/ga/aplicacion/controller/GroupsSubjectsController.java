package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.application.service.GroupSubjectsService;
import co.edu.cesde.ga.aplicacion.controller.dto.CreateGroupSubjectDto;
import co.edu.cesde.ga.aplicacion.controller.dto.response.GroupSubjectResponseDto;
import co.edu.cesde.ga.aplicacion.domain.GroupsSubjects;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-subjects")
public class GroupsSubjectsController {

    private final GroupSubjectsService groupSubjectsService;

    public GroupsSubjectsController(GroupSubjectsService groupSubjectsService) {
        this.groupSubjectsService = groupSubjectsService;
    }

    @GetMapping
    public ResponseEntity<List<GroupSubjectResponseDto>> findAll() {
        List<GroupSubjectResponseDto> assignments = groupSubjectsService.findAll()
                .stream()
                .map(GroupSubjectResponseDto::from)
                .toList();
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupSubjectResponseDto> findById(@PathVariable Long id) {
        GroupsSubjects assignment = groupSubjectsService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("una asignación de materia", id));
        return ResponseEntity.ok(GroupSubjectResponseDto.from(assignment));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<GroupSubjectResponseDto>> findByGroup(@PathVariable Long groupId) {
        List<GroupSubjectResponseDto> subjects = groupSubjectsService.findByGroup(groupId)
                .stream()
                .map(GroupSubjectResponseDto::from)
                .toList();
        return ResponseEntity.ok(subjects);
    }

    @PostMapping
    public ResponseEntity<GroupSubjectResponseDto> save(@Valid @RequestBody CreateGroupSubjectDto dto) {
        GroupsSubjects assignment = groupSubjectsService.assignSubject(dto.groupId(), dto.subjectId());
        return ResponseEntity.status(HttpStatus.CREATED).body(GroupSubjectResponseDto.from(assignment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        groupSubjectsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
