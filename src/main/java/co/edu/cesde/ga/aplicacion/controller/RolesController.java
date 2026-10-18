package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.application.service.RolesService;
import co.edu.cesde.ga.aplicacion.controller.dto.CreateRoleDto;
import co.edu.cesde.ga.aplicacion.controller.dto.UpdateRoleDto;
import co.edu.cesde.ga.aplicacion.controller.dto.response.RoleResponseDto;
import co.edu.cesde.ga.aplicacion.domain.Roles;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> findAll() {
        List<RoleResponseDto> roles = rolesService.findAll()
                .stream()
                .map(RoleResponseDto::from)
                .toList();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> findById(@PathVariable Long id) {
        Roles role = rolesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("un rol", id));
        return ResponseEntity.ok(RoleResponseDto.from(role));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RoleResponseDto> findByName(@PathVariable String name) {
        Roles role = rolesService.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un rol con el nombre: " + name));
        return ResponseEntity.ok(RoleResponseDto.from(role));
    }

    @PostMapping
    public ResponseEntity<RoleResponseDto> save(@Valid @RequestBody CreateRoleDto dto) {
        Roles role = rolesService.create(dto.toRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(RoleResponseDto.from(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateRoleDto dto) {
        Roles updated = rolesService.update(dto.toRole(id))
                .orElseThrow(() -> new ResourceNotFoundException("un rol", id));
        return ResponseEntity.ok(RoleResponseDto.from(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        rolesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
