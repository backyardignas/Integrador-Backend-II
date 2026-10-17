package co.edu.cesde.ga.aplicacion.controller.dto.response;

import co.edu.cesde.ga.aplicacion.domain.Periods;

import java.time.LocalDate;

public record PeriodResponseDto(Long id, String name, LocalDate startDate, LocalDate endDate) {

    public static PeriodResponseDto from(Periods period) {
        return new PeriodResponseDto(
                period.getPeriodId(),
                period.getName(),
                period.getStartDate(),
                period.getEndDate()
        );
    }
}
