package co.edu.cesde.ga.aplicacion.application.service;

import co.edu.cesde.ga.aplicacion.domain.Grades;

import java.util.List;
import java.util.Optional;

public interface GradesService {

    Grades create(Long studentId, Long groupSubjectId, Double finalScore, String observation);
    List<Grades> findAll();
    Optional<Grades> findById(Long id);
    List<Grades> findByStudent(Long studentId);
    Optional<Grades> update(Long id, Double finalScore, String observation);
    void deleteById(Long id);
    boolean existsById(Long id);
}
