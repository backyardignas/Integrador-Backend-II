package co.edu.cesde.ga.aplicacion.service;

import co.edu.cesde.ga.aplicacion.models.UserRoles;
import java.util.List;



public interface UserRolesService {

    void assignRole(Long userId, Long roleId);


    List<UserRoles> getRolesByUserId(Long userId);


    List<UserRoles> getAllAssignments();


    void removeRoleFromUser(Long userId, Long roleId);
}