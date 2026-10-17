package co.edu.cesde.ga.aplicacion.application.service;

import co.edu.cesde.ga.aplicacion.domain.Roles;

import java.util.List;
import java.util.Optional;

public interface RolesService {

    Roles create(Roles role);
    List<Roles> findAll();
    Optional<Roles> findById(Long id);
    Optional<Roles> findByName(String name);
    Optional<Roles> update(Roles role);
    void deleteById(Long id);
    boolean existsById(Long id);
}
