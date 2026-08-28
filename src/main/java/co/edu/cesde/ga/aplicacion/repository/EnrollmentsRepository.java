package co.edu.cesde.ga.aplicacion.repository;

import co.edu.cesde.ga.aplicacion.models.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {

    List<Enrollments> findByStudent_StudentId(Long studentId);
}