package co.edu.cesde.ga.aplicacion.service;

import co.edu.cesde.ga.aplicacion.models.GroupsSubjects; // Nota la 's'
import java.util.List;

public interface GroupSubjectsService {
    void assignSubjectToGroup(GroupsSubjects groupSubject);
    GroupsSubjects getAssignmentById(Long id);
    List<GroupsSubjects> getSubjectsByGroup(Long groupId);
    List<GroupsSubjects> getAllAssignments();
    void removeAssignment(Long id);
}