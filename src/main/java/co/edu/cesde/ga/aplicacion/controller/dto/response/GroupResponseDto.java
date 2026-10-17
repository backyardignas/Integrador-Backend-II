package co.edu.cesde.ga.aplicacion.controller.dto.response;

import co.edu.cesde.ga.aplicacion.domain.Groups;

public record GroupResponseDto(
        Long id,
        String code,
        String shift,
        Long programId,
        String programName,
        Long periodId,
        String periodName
) {
    public static GroupResponseDto from(Groups group) {
        return new GroupResponseDto(
                group.getGroupId(),
                group.getCode(),
                group.getShift(),
                group.getProgram().getProgramId(),
                group.getProgram().getName(),
                group.getPeriod().getPeriodId(),
                group.getPeriod().getName()
        );
    }
}
