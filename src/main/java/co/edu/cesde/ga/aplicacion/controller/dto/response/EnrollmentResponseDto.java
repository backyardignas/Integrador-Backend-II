package co.edu.cesde.ga.aplicacion.controller.dto.response;

import co.edu.cesde.ga.aplicacion.domain.EnrollmentStatus;
import co.edu.cesde.ga.aplicacion.domain.Enrollments;

import java.time.LocalDate;

public record EnrollmentResponseDto(
        Long id,
        Long studentId,
        String studentName,
        Long subjectId,
        String subjectName,
        Long periodId,
        String periodName,
        LocalDate enrollmentDate,
        EnrollmentStatus status
) {
    public static EnrollmentResponseDto from(Enrollments enrollment) {
        return new EnrollmentResponseDto(
                enrollment.getEnrollmentId(),
                enrollment.getStudent().getStudentId(),
                enrollment.getStudent().getFirstName() + " " + enrollment.getStudent().getLastName(),
                enrollment.getSubject().getSubjectId(),
                enrollment.getSubject().getName(),
                enrollment.getPeriod().getPeriodId(),
                enrollment.getPeriod().getName(),
                enrollment.getEnrollmentDate(),
                enrollment.getStatus()
        );
    }
}
