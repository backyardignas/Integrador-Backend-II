package co.edu.cesde.ga.aplicacion.application.service.Impl;

import co.edu.cesde.ga.aplicacion.application.service.GroupSubjectsService;
import co.edu.cesde.ga.aplicacion.domain.Groups;
import co.edu.cesde.ga.aplicacion.domain.GroupsSubjects;
import co.edu.cesde.ga.aplicacion.domain.Subjects;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceConflictException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.repository.GroupSubjectsRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.GroupsRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.SubjectsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupSubjectsServiceImpl implements GroupSubjectsService {

    private final GroupSubjectsRepository groupSubjectRepository;
    private final GroupsRepository groupsRepository;
    private final SubjectsRepository subjectsRepository;

    public GroupSubjectsServiceImpl(GroupSubjectsRepository groupSubjectRepository,
                                    GroupsRepository groupsRepository,
                                    SubjectsRepository subjectsRepository) {
        this.groupSubjectRepository = groupSubjectRepository;
        this.groupsRepository = groupsRepository;
        this.subjectsRepository = subjectsRepository;
    }

    @Override
    public GroupsSubjects assignSubject(Long groupId, Long subjectId) {
        Groups group = groupsRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("un grupo", groupId));
        Subjects subject = subjectsRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("una materia", subjectId));

        if (groupSubjectRepository.existsByGroup_GroupIdAndSubject_SubjectId(groupId, subjectId)) {
            throw new ResourceConflictException("La materia " + subject.getName() + " ya está asignada al grupo " + group.getCode());
        }

        return groupSubjectRepository.save(new GroupsSubjects(null, group, subject));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupsSubjects> findAll() {
        return groupSubjectRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GroupsSubjects> findById(Long id) {
        return groupSubjectRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupsSubjects> findByGroup(Long groupId) {
        if (!groupsRepository.existsById(groupId)) {
            throw new ResourceNotFoundException("un grupo", groupId);
        }
        return groupSubjectRepository.findByGroup_GroupId(groupId);
    }

    @Override
    public void deleteById(Long id) {
        if (!groupSubjectRepository.existsById(id)) {
            throw new ResourceNotFoundException("una asignación de materia", id);
        }
        groupSubjectRepository.deleteById(id);
    }
}
