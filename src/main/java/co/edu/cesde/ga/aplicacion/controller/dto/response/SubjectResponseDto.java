package co.edu.cesde.ga.aplicacion.controller.dto.response;

import co.edu.cesde.ga.aplicacion.domain.Subjects;

public record SubjectResponseDto(
        Long id,
        String code,
        String name,
        Integer credits,
        Long programId,
        String programName
) {
    public static SubjectResponseDto from(Subjects subject) {
        return new SubjectResponseDto(
                subject.getSubjectId(),
                subject.getCode(),
                subject.getName(),
                subject.getCredits(),
                subject.getProgram() != null ? subject.getProgram().getProgramId() : null,
                subject.getProgram() != null ? subject.getProgram().getName() : null
        );
    }
}
