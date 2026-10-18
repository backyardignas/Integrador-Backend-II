package co.edu.cesde.ga.aplicacion.application.service;

import co.edu.cesde.ga.aplicacion.domain.Periods;

import java.util.List;
import java.util.Optional;

public interface PeriodsService {

    Periods create(Periods period);
    List<Periods> findAll();
    Optional<Periods> findById(Long id);
    Optional<Periods> update(Periods period);
    void deleteById(Long id);
    boolean existsById(Long id);
}
