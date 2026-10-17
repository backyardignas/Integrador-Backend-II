package co.edu.cesde.ga.aplicacion.domain.repository;

import co.edu.cesde.ga.aplicacion.domain.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers, Long> {

    Optional<Teachers> findByDocumentNumber(String documentNumber);

    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByDocumentNumberAndTeacherIdNot(String documentNumber, Long teacherId);

    boolean existsByCode(String code);

    boolean existsByCodeAndTeacherIdNot(String code, Long teacherId);
}
