package co.edu.cesde.ga.aplicacion.controller.dto.response;

import co.edu.cesde.ga.aplicacion.domain.Roles;

public record RoleResponseDto(Long id, String name, String description) {

    public static RoleResponseDto from(Roles role) {
        return new RoleResponseDto(role.getRoleId(), role.getName(), role.getDescription());
    }
}
