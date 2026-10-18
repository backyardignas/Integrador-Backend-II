package co.edu.cesde.ga.aplicacion.application.service;

import co.edu.cesde.ga.aplicacion.domain.UserRoles;

import java.util.List;
import java.util.Optional;

public interface UserRolesService {

    UserRoles assignRole(Long userId, Long roleId);
    List<UserRoles> findAll();
    Optional<UserRoles> findById(Long id);
    List<UserRoles> findByUser(Long userId);
    void deleteById(Long id);
}
