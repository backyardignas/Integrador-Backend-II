package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.application.service.UserService;
import co.edu.cesde.ga.aplicacion.controller.dto.CreateUserDto;
import co.edu.cesde.ga.aplicacion.controller.dto.UpdateUserDto;
import co.edu.cesde.ga.aplicacion.controller.dto.response.UserResponseDto;
import co.edu.cesde.ga.aplicacion.domain.Users;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> users = userService.findAll()
                .stream()
                .map(UserResponseDto::from)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        Users user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("un usuario", id));
        return ResponseEntity.ok(UserResponseDto.from(user));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> findByEmail(@PathVariable String email) {
        Users user = userService.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario con el correo: " + email));
        return ResponseEntity.ok(UserResponseDto.from(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody CreateUserDto dto) {
        Users user = userService.create(dto.toUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseDto.from(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateUserDto dto) {
        Users updated = userService.update(dto.toUser(id))
                .orElseThrow(() -> new ResourceNotFoundException("un usuario", id));
        return ResponseEntity.ok(UserResponseDto.from(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
