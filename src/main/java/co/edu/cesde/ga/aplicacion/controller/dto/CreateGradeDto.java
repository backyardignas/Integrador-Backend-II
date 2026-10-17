package co.edu.cesde.ga.aplicacion.controller.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateGradeDto(
        @NotNull Long studentId,
        @NotNull Long groupSubjectId,
        @NotNull @DecimalMin("0.0") @DecimalMax("5.0") Double finalScore,
        @Size(max = 255) String observation
) {
}
