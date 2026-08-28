package co.edu.cesde.ga.aplicacion.service;
import co.edu.cesde.ga.aplicacion.models.Teachers;

import java.util.List;

public interface TeacherService {

    Teachers create (Teachers teacher);

    boolean delete(Long teacherId);

    boolean update(Teachers teacherUpdate);

    Teachers findById(Long teacherId);

    List<Teachers> findAll();
}