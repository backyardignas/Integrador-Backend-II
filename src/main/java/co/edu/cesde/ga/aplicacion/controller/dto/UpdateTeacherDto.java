package co.edu.cesde.ga.aplicacion.controller.dto;

import co.edu.cesde.ga.aplicacion.domain.Teachers;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTeacherDto(
        @NotBlank @Size(max = 100) String firstName,
        @NotBlank @Size(max = 100) String lastName,
        @NotBlank @Size(max = 50) String documentNumber,
        @NotBlank @Size(max = 50) String code,
        @NotBlank @Size(max = 50) String status,
        Long userId
) {
    public Teachers toTeacher(Long id) {
        return new Teachers(id, null, firstName.trim(), lastName.trim(), documentNumber.trim(),
                code.trim().toUpperCase(), status.trim().toUpperCase());
    }
}
