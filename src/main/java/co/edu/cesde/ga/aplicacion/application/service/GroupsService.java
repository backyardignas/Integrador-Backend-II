package co.edu.cesde.ga.aplicacion.application.service;

import co.edu.cesde.ga.aplicacion.domain.Groups;

import java.util.List;
import java.util.Optional;

public interface GroupsService {

    Groups create(Groups group, Long programId, Long periodId);
    List<Groups> findAll();
    Optional<Groups> findById(Long id);
    Optional<Groups> update(Groups group, Long programId, Long periodId);
    void deleteById(Long id);
    boolean existsById(Long id);
}
