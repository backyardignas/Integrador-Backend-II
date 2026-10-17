package co.edu.cesde.ga.aplicacion.application.service;

import co.edu.cesde.ga.aplicacion.domain.Subjects;

import java.util.List;
import java.util.Optional;

public interface SubjectsService {

    Subjects create(Subjects subject, Long programId);
    List<Subjects> findAll();
    Optional<Subjects> findById(Long id);
    Optional<Subjects> update(Subjects subject, Long programId);
    void deleteById(Long id);
    boolean existsById(Long id);
}
