package co.edu.cesde.ga.aplicacion.service;

import co.edu.cesde.ga.aplicacion.models.Students;

import java.util.List;

public interface StudentService {

    Students create (Students student);

    boolean delete(Long studentId);

    boolean update(Students studentUpdate);

    Students findById(Long studentId);

    List<Students> findAll();
}