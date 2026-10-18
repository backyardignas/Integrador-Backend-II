package co.edu.cesde.ga.aplicacion.controller.dto;

import jakarta.validation.constraints.NotNull;

public record CreateUserRoleDto(
        @NotNull Long userId,
        @NotNull Long roleId
) {
}
