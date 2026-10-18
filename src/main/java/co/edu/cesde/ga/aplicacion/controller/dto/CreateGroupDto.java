package co.edu.cesde.ga.aplicacion.controller.dto;

import co.edu.cesde.ga.aplicacion.domain.Groups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateGroupDto(
        @NotBlank @Size(max = 50) String code,
        @NotNull Long programId,
        @NotNull Long periodId,
        @NotBlank @Size(max = 50) String shift
) {
    public Groups toGroup() {
        return new Groups(null, code.trim().toUpperCase(), null, null, shift.trim().toUpperCase());
    }
}
