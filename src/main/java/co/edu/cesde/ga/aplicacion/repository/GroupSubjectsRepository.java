package co.edu.cesde.ga.aplicacion.repository;

import co.edu.cesde.ga.aplicacion.models.GroupsSubjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupSubjectsRepository extends JpaRepository<GroupsSubjects, Long> {
    List<GroupsSubjects> findByGroup_GroupId(Long groupId);
}