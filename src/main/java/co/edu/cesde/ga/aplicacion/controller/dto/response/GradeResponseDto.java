package co.edu.cesde.ga.aplicacion.controller.dto.response;

import co.edu.cesde.ga.aplicacion.domain.Grades;

public record GradeResponseDto(
        Long id,
        Long studentId,
        String studentName,
        Long groupSubjectId,
        String groupCode,
        String subjectName,
        Double finalScore,
        String observation
) {
    public static GradeResponseDto from(Grades grade) {
        return new GradeResponseDto(
                grade.getGradeId(),
                grade.getStudent().getStudentId(),
                grade.getStudent().getFirstName() + " " + grade.getStudent().getLastName(),
                grade.getGroupSubject().getGroupSubjectId(),
                grade.getGroupSubject().getGroup().getCode(),
                grade.getGroupSubject().getSubject().getName(),
                grade.getFinalScore(),
                grade.getObservation()
        );
    }
}
