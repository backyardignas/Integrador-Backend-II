package co.edu.cesde.ga.aplicacion.domain.repository;

import co.edu.cesde.ga.aplicacion.domain.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    List<UserRoles> findByUser_UserId(Long userId);

    boolean existsByUser_UserIdAndRole_RoleId(Long userId, Long roleId);
}
