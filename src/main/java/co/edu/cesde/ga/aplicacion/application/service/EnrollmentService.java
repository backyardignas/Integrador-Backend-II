package co.edu.cesde.ga.aplicacion.application.service;

import co.edu.cesde.ga.aplicacion.domain.EnrollmentStatus;
import co.edu.cesde.ga.aplicacion.domain.Enrollments;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EnrollmentService {

    Enrollments create(Long studentId, Long subjectId, Long periodId, LocalDate enrollmentDate);
    List<Enrollments> findAll();
    Optional<Enrollments> findById(Long id);
    List<Enrollments> findByStudent(Long studentId);
    Enrollments updateStatus(Long id, EnrollmentStatus status);
    void deleteById(Long id);
    boolean existsById(Long id);
}
