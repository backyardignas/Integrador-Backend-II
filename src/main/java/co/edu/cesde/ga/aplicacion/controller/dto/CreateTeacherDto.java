package co.edu.cesde.ga.aplicacion.controller.dto;

import co.edu.cesde.ga.aplicacion.domain.Teachers;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTeacherDto(
        @NotBlank @Size(max = 100) String firstName,
        @NotBlank @Size(max = 100) String lastName,
        @NotBlank @Size(max = 50) String documentNumber,
        @NotBlank @Size(max = 50) String code,
        @Size(max = 50) String status,
        Long userId
) {
    public Teachers toTeacher() {
        String estado = (status == null || status.isBlank()) ? "ACTIVO" : status.trim().toUpperCase();
        return new Teachers(null, null, firstName.trim(), lastName.trim(), documentNumber.trim(),
                code.trim().toUpperCase(), estado);
    }
}
