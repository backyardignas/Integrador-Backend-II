package co.edu.cesde.ga.aplicacion.service;

import co.edu.cesde.ga.aplicacion.models.Subjects;

import java.util.List;

public interface SubjectsService {

    Subjects create(Subjects subject);

    boolean delete(Long subjectId);

    boolean update(Subjects subjectUpdate);

    Subjects findById(Long subjectId);

    List<Subjects> findAll();
    boolean existsById(Long subjectId);
}
