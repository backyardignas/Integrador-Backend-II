package co.edu.cesde.ga.aplicacion.application.service;

import co.edu.cesde.ga.aplicacion.domain.Programs;

import java.util.List;
import java.util.Optional;

public interface ProgramService {

    Programs create(Programs program);
    List<Programs> findAll();
    Optional<Programs> findById(Long id);
    Optional<Programs> update(Programs program);
    void deleteById(Long id);
    boolean existsById(Long id);
}
