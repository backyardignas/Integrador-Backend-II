package co.edu.cesde.ga.aplicacion.application.service;

import co.edu.cesde.ga.aplicacion.domain.Teachers;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    Teachers create(Teachers teacher, Long userId);
    List<Teachers> findAll();
    Optional<Teachers> findById(Long id);
    Optional<Teachers> update(Teachers teacher, Long userId);
    void deleteById(Long id);
    boolean existsById(Long id);
}
