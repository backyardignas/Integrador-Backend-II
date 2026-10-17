package co.edu.cesde.ga.aplicacion.application.service;

import co.edu.cesde.ga.aplicacion.domain.Students;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Students create(Students student, Long userId);
    List<Students> findAll();
    Optional<Students> findById(Long id);
    Optional<Students> update(Students student, Long userId);
    void deleteById(Long id);
    boolean existsById(Long id);
}
