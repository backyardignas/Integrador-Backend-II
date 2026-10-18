package co.edu.cesde.ga.aplicacion.controller.dto;

import co.edu.cesde.ga.aplicacion.domain.Periods;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdatePeriodDto(
        @NotBlank String name,
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate
) {
    public Periods toPeriod(Long id) {
        return new Periods(id, name.trim(), startDate, endDate);
    }
}
