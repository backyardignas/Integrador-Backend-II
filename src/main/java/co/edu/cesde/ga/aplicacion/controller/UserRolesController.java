package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.models.UserRoles;
import co.edu.cesde.ga.aplicacion.service.UserRolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@CrossOrigin(origins = "*")
public class UserRolesController {

    private final UserRolesService userRolesService;

    public UserRolesController(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }

    // GET /api/user-roles -> lista todas las asignaciones usuario-rol
    @GetMapping
    public ResponseEntity<List<UserRoles>> findAll() {
        return ResponseEntity.ok(userRolesService.getAllAssignments());
    }

    // GET /api/user-roles/user/{userId} -> roles asignados a un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRoles>> findByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userRolesService.getRolesByUserId(userId));
    }

    // POST /api/user-roles/{userId}/{roleId} -> asigna un rol a un usuario
    // (el servicio recibe los ids sueltos en lugar de un objeto UserRoles, así que no hay @RequestBody aquí)
    @PostMapping("/{userId}/{roleId}")
    public ResponseEntity<Void> assign(@PathVariable Long userId, @PathVariable Long roleId) {
        userRolesService.assignRole(userId, roleId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // DELETE /api/user-roles/{userId}/{roleId} -> revoca un rol de un usuario
    @DeleteMapping("/{userId}/{roleId}")
    public ResponseEntity<Void> remove(@PathVariable Long userId, @PathVariable Long roleId) {
        userRolesService.removeRoleFromUser(userId, roleId);
        return ResponseEntity.noContent().build();
    }
}
