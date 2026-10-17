package co.edu.cesde.ga.aplicacion.controller.dto;

import co.edu.cesde.ga.aplicacion.domain.Subjects;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateSubjectDto(
        @NotBlank String code,
        @NotBlank String name,
        @NotNull @Positive Integer credits,
        @NotNull Long programId
) {
    public Subjects toSubject() {
        return new Subjects(null, code.trim().toUpperCase(), name.trim(), credits, null);
    }
}
