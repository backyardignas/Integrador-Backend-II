package co.edu.cesde.ga.aplicacion.controller.dto;

import co.edu.cesde.ga.aplicacion.domain.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotBlank @Size(max = 50) String identification,
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Size(max = 100) String lastName,
        @NotBlank @Email @Size(max = 150) String email,
        @NotBlank @Size(min = 6, max = 72) String password
) {
    public Users toUser() {
        return new Users(null, identification.trim(), name.trim(), lastName.trim(), email.trim().toLowerCase(), password);
    }
}
