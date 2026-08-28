package co.edu.cesde.ga.aplicacion.service;

import co.edu.cesde.ga.aplicacion.models.Roles;
import java.util.List;


public interface RolesService {

    void createRole(Roles role);


    Roles getRoleById(Long id);


    Roles getRoleByName(String name);


    List<Roles> getAllRoles();


    void deleteRole(Long id);
}
