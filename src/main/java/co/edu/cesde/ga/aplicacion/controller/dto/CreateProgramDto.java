package co.edu.cesde.ga.aplicacion.controller.dto;

import co.edu.cesde.ga.aplicacion.domain.Programs;
import jakarta.validation.constraints.NotBlank;

public record CreateProgramDto(
        @NotBlank String code,
        @NotBlank String name
) {
    public Programs toProgram() {
        return new Programs(null, code.trim().toUpperCase(), name.trim());
    }
}
