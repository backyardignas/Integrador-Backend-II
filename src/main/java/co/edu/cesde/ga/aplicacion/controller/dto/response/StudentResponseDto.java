package co.edu.cesde.ga.aplicacion.controller.dto.response;

import co.edu.cesde.ga.aplicacion.domain.Students;

import java.time.LocalDate;

public record StudentResponseDto(
        Long id,
        String firstName,
        String lastName,
        String documentNumber,
        String code,
        LocalDate birthDate,
        String status,
        Long userId
) {
    public static StudentResponseDto from(Students student) {
        return new StudentResponseDto(
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getDocumentNumber(),
                student.getCode(),
                student.getBirthDate(),
                student.getStatus(),
                student.getUser() != null ? student.getUser().getUserId() : null
        );
    }
}
