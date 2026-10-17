package co.edu.cesde.ga.aplicacion.controller.dto;

import jakarta.validation.constraints.NotNull;

public record CreateGroupSubjectDto(
        @NotNull Long groupId,
        @NotNull Long subjectId
) {
}
