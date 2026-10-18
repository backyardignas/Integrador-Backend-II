package co.edu.cesde.ga.aplicacion.controller.dto.response;

import co.edu.cesde.ga.aplicacion.domain.Teachers;

public record TeacherResponseDto(
        Long id,
        String firstName,
        String lastName,
        String documentNumber,
        String code,
        String status,
        Long userId
) {
    public static TeacherResponseDto from(Teachers teacher) {
        return new TeacherResponseDto(
                teacher.getTeacherId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getDocumentNumber(),
                teacher.getCode(),
                teacher.getStatus(),
                teacher.getUser() != null ? teacher.getUser().getUserId() : null
        );
    }
}
