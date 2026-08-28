package co.edu.cesde.ga.aplicacion.service;

import co.edu.cesde.ga.aplicacion.models.Grades;
import java.util.List;
public interface GradesService {

    Grades create (Grades grades);

    boolean delete(Long gradeId);

    boolean update(Grades gradeUpdate);

    Grades findById(Long gradeId);

    List<Grades> findAll();

    boolean existsById(Long gradeId);

}
