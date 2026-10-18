package co.edu.cesde.ga.aplicacion.domain.repository;

import co.edu.cesde.ga.aplicacion.domain.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Long> {

    boolean existsByCode(String code);

    boolean existsByCodeAndSubjectIdNot(String code, Long subjectId);
}
