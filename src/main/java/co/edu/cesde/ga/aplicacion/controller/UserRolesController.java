package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.application.service.UserRolesService;
import co.edu.cesde.ga.aplicacion.controller.dto.CreateUserRoleDto;
import co.edu.cesde.ga.aplicacion.controller.dto.response.UserRoleResponseDto;
import co.edu.cesde.ga.aplicacion.domain.UserRoles;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRolesController {

    private final UserRolesService userRolesService;

    public UserRolesController(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }

    @GetMapping
    public ResponseEntity<List<UserRoleResponseDto>> findAll() {
        List<UserRoleResponseDto> assignments = userRolesService.findAll()
                .stream()
                .map(UserRoleResponseDto::from)
                .toList();
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleResponseDto> findById(@PathVariable Long id) {
        UserRoles userRole = userRolesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("una asignación de rol", id));
        return ResponseEntity.ok(UserRoleResponseDto.from(userRole));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRoleResponseDto>> findByUser(@PathVariable Long userId) {
        List<UserRoleResponseDto> roles = userRolesService.findByUser(userId)
                .stream()
                .map(UserRoleResponseDto::from)
                .toList();
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity<UserRoleResponseDto> assign(@Valid @RequestBody CreateUserRoleDto dto) {
        UserRoles userRole = userRolesService.assignRole(dto.userId(), dto.roleId());
        return ResponseEntity.status(HttpStatus.CREATED).body(UserRoleResponseDto.from(userRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userRolesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
