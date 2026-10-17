package co.edu.cesde.ga.aplicacion.controller.dto;

import co.edu.cesde.ga.aplicacion.domain.Roles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateRoleDto(
        @NotBlank @Size(max = 50) String name,
        @Size(max = 255) String description
) {
    public Roles toRole(Long id) {
        return new Roles(id, name.trim().toUpperCase(), description);
    }
}
