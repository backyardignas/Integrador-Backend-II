package co.edu.cesde.ga.aplicacion.controller.dto;

import co.edu.cesde.ga.aplicacion.domain.EnrollmentStatus;
import jakarta.validation.constraints.NotBlank;

public record UpdateEnrollmentDto(@NotBlank String status) {

    public EnrollmentStatus toStatus() {
        return EnrollmentStatus.from(status);
    }
}
