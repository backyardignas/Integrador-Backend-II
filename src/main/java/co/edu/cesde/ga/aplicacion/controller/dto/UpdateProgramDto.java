package co.edu.cesde.ga.aplicacion.controller.dto;

import co.edu.cesde.ga.aplicacion.domain.Programs;
import jakarta.validation.constraints.NotBlank;

public record UpdateProgramDto(
        @NotBlank String code,
        @NotBlank String name
) {
    public Programs toProgram(Long id) {
        return new Programs(id, code.trim().toUpperCase(), name.trim());
    }
}
