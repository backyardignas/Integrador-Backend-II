package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.application.service.GroupsService;
import co.edu.cesde.ga.aplicacion.controller.dto.CreateGroupDto;
import co.edu.cesde.ga.aplicacion.controller.dto.UpdateGroupDto;
import co.edu.cesde.ga.aplicacion.controller.dto.response.GroupResponseDto;
import co.edu.cesde.ga.aplicacion.domain.Groups;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupsController {

    private final GroupsService groupsService;

    public GroupsController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @GetMapping
    public ResponseEntity<List<GroupResponseDto>> findAll() {
        List<GroupResponseDto> groups = groupsService.findAll()
                .stream()
                .map(GroupResponseDto::from)
                .toList();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDto> findById(@PathVariable Long id) {
        Groups group = groupsService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("un grupo", id));
        return ResponseEntity.ok(GroupResponseDto.from(group));
    }

    @PostMapping
    public ResponseEntity<GroupResponseDto> save(@Valid @RequestBody CreateGroupDto dto) {
        Groups group = groupsService.create(dto.toGroup(), dto.programId(), dto.periodId());
        return ResponseEntity.status(HttpStatus.CREATED).body(GroupResponseDto.from(group));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateGroupDto dto) {
        Groups updated = groupsService.update(dto.toGroup(id), dto.programId(), dto.periodId())
                .orElseThrow(() -> new ResourceNotFoundException("un grupo", id));
        return ResponseEntity.ok(GroupResponseDto.from(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        groupsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
