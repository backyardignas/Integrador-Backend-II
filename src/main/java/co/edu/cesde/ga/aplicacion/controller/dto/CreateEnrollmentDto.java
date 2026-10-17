package co.edu.cesde.ga.aplicacion.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CreateEnrollmentDto(
        @NotNull Long studentId,
        @NotNull Long subjectId,
        @NotNull Long periodId,
        @PastOrPresent
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate enrollmentDate
) {
}
