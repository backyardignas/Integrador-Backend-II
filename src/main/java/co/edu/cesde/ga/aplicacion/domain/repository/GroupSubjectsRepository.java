package co.edu.cesde.ga.aplicacion.domain.repository;

import co.edu.cesde.ga.aplicacion.domain.GroupsSubjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupSubjectsRepository extends JpaRepository<GroupsSubjects, Long> {

    List<GroupsSubjects> findByGroup_GroupId(Long groupId);

    boolean existsByGroup_GroupIdAndSubject_SubjectId(Long groupId, Long subjectId);
}
