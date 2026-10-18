package co.edu.cesde.ga.aplicacion.controller.dto.response;

import co.edu.cesde.ga.aplicacion.domain.UserRoles;

public record UserRoleResponseDto(
        Long id,
        Long userId,
        String userEmail,
        Long roleId,
        String roleName
) {
    public static UserRoleResponseDto from(UserRoles userRole) {
        return new UserRoleResponseDto(
                userRole.getId(),
                userRole.getUser().getUserId(),
                userRole.getUser().getEmail(),
                userRole.getRole().getRoleId(),
                userRole.getRole().getName()
        );
    }
}
