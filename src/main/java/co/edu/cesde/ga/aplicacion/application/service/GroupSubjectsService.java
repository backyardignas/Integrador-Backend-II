package co.edu.cesde.ga.aplicacion.application.service;

import co.edu.cesde.ga.aplicacion.domain.GroupsSubjects;

import java.util.List;
import java.util.Optional;

public interface GroupSubjectsService {

    GroupsSubjects assignSubject(Long groupId, Long subjectId);
    List<GroupsSubjects> findAll();
    Optional<GroupsSubjects> findById(Long id);
    List<GroupsSubjects> findByGroup(Long groupId);
    void deleteById(Long id);
}
