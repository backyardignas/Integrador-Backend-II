package co.edu.cesde.ga.aplicacion.service;

import co.edu.cesde.ga.aplicacion.models.Groups;

import java.util.List;
public interface GroupsService {

    Groups create (Groups groups);

    boolean delete(Long groupsId);

    boolean update(Groups groupsUpdate);

    Groups findById(Long groupsId);

    List<Groups> findAll();

    boolean existsById(Long groupsId);

}
