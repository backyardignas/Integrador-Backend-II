package co.edu.cesde.ga.aplicacion.domain.repository;

import co.edu.cesde.ga.aplicacion.domain.EnrollmentStatus;
import co.edu.cesde.ga.aplicacion.domain.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {

    List<Enrollments> findByStudent_StudentId(Long studentId);

    boolean existsByStudent_StudentIdAndSubject_SubjectIdAndPeriod_PeriodIdAndStatusNot(
            Long studentId, Long subjectId, Long periodId, EnrollmentStatus status);

    boolean existsByStudent_StudentIdAndSubject_SubjectIdAndPeriod_PeriodIdAndStatusNotAndEnrollmentIdNot(
            Long studentId, Long subjectId, Long periodId, EnrollmentStatus status, Long enrollmentId);
}
