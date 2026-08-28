package co.edu.cesde.ga.aplicacion.repository;

import co.edu.cesde.ga.aplicacion.models.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers, Long> {

    Optional<Teachers> findByDocumentNumber(String documentNumber);

    boolean existsByDocumentNumber(String documentNumber);
}