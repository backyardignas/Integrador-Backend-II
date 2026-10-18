package co.edu.cesde.ga.aplicacion.controller.dto.response;

import co.edu.cesde.ga.aplicacion.domain.Users;

public record UserResponseDto(Long id, String identification, String name, String lastName, String email) {

    public static UserResponseDto from(Users user) {
        return new UserResponseDto(
                user.getUserId(),
                user.getIdentification(),
                user.getName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
