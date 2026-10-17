package co.edu.cesde.ga.aplicacion.controller.dto;

import co.edu.cesde.ga.aplicacion.domain.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserDto(
        @NotBlank @Size(max = 50) String identification,
        @NotBlank String name,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String password
) {
    public Users toUser(Long id) {
        return new Users(id, identification.trim(), name.trim(), lastName.trim(), email.trim().toLowerCase(), password);
    }
}
