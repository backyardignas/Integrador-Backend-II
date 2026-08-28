package co.edu.cesde.ga.aplicacion.controller;

import co.edu.cesde.ga.aplicacion.models.Users;
import co.edu.cesde.ga.aplicacion.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users -> lista todos los usuarios
    @GetMapping
    public ResponseEntity<List<Users>> findAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET /api/users/{id} -> busca un usuario por su id
    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id) {
        Users user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // GET /api/users/email/{email} -> busca un usuario por su correo
    @GetMapping("/email/{email}")
    public ResponseEntity<Users> findByEmail(@PathVariable String email) {
        Users user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // POST /api/users -> registra un nuevo usuario (registerUser es void: se devuelve el objeto recibido)
    @PostMapping
    public ResponseEntity<Users> save(@RequestBody Users user) {
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // PUT /api/users/{id} -> actualiza los datos de un usuario
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Users user) {
        user.setUserId(id);
        userService.updateUserInfo(user);
        return ResponseEntity.noContent().build();
    }

    // DELETE /api/users/{id} -> elimina un usuario por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }
}
