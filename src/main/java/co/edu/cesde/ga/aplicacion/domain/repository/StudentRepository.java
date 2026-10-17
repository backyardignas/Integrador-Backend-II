package co.edu.cesde.ga.aplicacion.domain.repository;

import co.edu.cesde.ga.aplicacion.domain.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {

    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByDocumentNumberAndStudentIdNot(String documentNumber, Long studentId);

    boolean existsByCode(String code);

    boolean existsByCodeAndStudentIdNot(String code, Long studentId);
}
