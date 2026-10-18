package co.edu.cesde.ga.aplicacion.domain.repository;

import co.edu.cesde.ga.aplicacion.domain.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Long> {

    List<Grades> findByStudent_StudentId(Long studentId);

    boolean existsByStudent_StudentIdAndGroupSubject_GroupSubjectId(Long studentId, Long groupSubjectId);
}
