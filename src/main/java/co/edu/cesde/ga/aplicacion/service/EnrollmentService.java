package co.edu.cesde.ga.aplicacion.service;

import co.edu.cesde.ga.aplicacion.models.Enrollments;
import java.util.List;


public interface EnrollmentService {


    void enrollStudent(Enrollments enrollment);


    Enrollments getEnrollmentById(Long id);


    List<Enrollments> getEnrollmentsByStudent(Long studentId);


    List<Enrollments> getAllEnrollments();


    void cancelEnrollment(Long id);
}
