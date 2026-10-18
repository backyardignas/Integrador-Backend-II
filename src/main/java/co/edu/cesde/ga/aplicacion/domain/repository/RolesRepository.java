package co.edu.cesde.ga.aplicacion.domain.repository;

import co.edu.cesde.ga.aplicacion.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByName(String name);

    boolean existsByName(String name);

    boolean existsByNameAndRoleIdNot(String name, Long roleId);
}
