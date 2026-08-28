package co.edu.cesde.ga.aplicacion.service.Impl;

import co.edu.cesde.ga.aplicacion.models.GroupsSubjects;
import co.edu.cesde.ga.aplicacion.repository.GroupSubjectsRepository;
import co.edu.cesde.ga.aplicacion.service.GroupSubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupSubjectsServiceImpl implements GroupSubjectsService {

    private final GroupSubjectsRepository groupSubjectRepository;

    @Autowired
    public GroupSubjectsServiceImpl(GroupSubjectsRepository groupSubjectRepository) {
        this.groupSubjectRepository = groupSubjectRepository;
    }

    @Override
    @Transactional
    public void assignSubjectToGroup(GroupsSubjects groupSubject) {
        groupSubjectRepository.save(groupSubject);
    }

    @Override
    public GroupsSubjects getAssignmentById(Long id) {
        return groupSubjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<GroupsSubjects> getSubjectsByGroup(Long groupId) {
        return groupSubjectRepository.findByGroup_GroupId(groupId);
    }

    @Override
    public List<GroupsSubjects> getAllAssignments() {
        return groupSubjectRepository.findAll();
    }

    @Override
    @Transactional
    public void removeAssignment(Long id) {
        groupSubjectRepository.deleteById(id);
    }
}
