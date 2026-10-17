package co.edu.cesde.ga.aplicacion.application.service.Impl;

import co.edu.cesde.ga.aplicacion.application.service.GroupsService;
import co.edu.cesde.ga.aplicacion.domain.Groups;
import co.edu.cesde.ga.aplicacion.domain.Periods;
import co.edu.cesde.ga.aplicacion.domain.Programs;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceConflictException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.repository.GroupsRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.PeriodsRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupsServiceImpl implements GroupsService {

    private final GroupsRepository groupsRepository;
    private final ProgramRepository programRepository;
    private final PeriodsRepository periodsRepository;

    public GroupsServiceImpl(GroupsRepository groupsRepository,
                             ProgramRepository programRepository,
                             PeriodsRepository periodsRepository) {
        this.groupsRepository = groupsRepository;
        this.programRepository = programRepository;
        this.periodsRepository = periodsRepository;
    }

    @Override
    public Groups create(Groups group, Long programId, Long periodId) {
        if (groupsRepository.existsByCode(group.getCode())) {
            throw new ResourceConflictException("Ya existe un grupo con el código: " + group.getCode());
        }
        group.setProgram(findProgram(programId));
        group.setPeriod(findPeriod(periodId));
        return groupsRepository.save(group);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Groups> findAll() {
        return groupsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Groups> findById(Long id) {
        return groupsRepository.findById(id);
    }

    @Override
    public Optional<Groups> update(Groups group, Long programId, Long periodId) {
        Optional<Groups> existing = groupsRepository.findById(group.getGroupId());
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        if (groupsRepository.existsByCodeAndGroupIdNot(group.getCode(), group.getGroupId())) {
            throw new ResourceConflictException("Ya existe un grupo con el código: " + group.getCode());
        }

        Groups current = existing.get();
        current.setCode(group.getCode());
        current.setShift(group.getShift());
        current.setProgram(findProgram(programId));
        current.setPeriod(findPeriod(periodId));

        return Optional.of(groupsRepository.save(current));
    }

    @Override
    public void deleteById(Long id) {
        if (!groupsRepository.existsById(id)) {
            throw new ResourceNotFoundException("un grupo", id);
        }
        groupsRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return groupsRepository.existsById(id);
    }

    private Programs findProgram(Long programId) {
        return programRepository.findById(programId)
                .orElseThrow(() -> new ResourceNotFoundException("un programa", programId));
    }

    private Periods findPeriod(Long periodId) {
        return periodsRepository.findById(periodId)
                .orElseThrow(() -> new ResourceNotFoundException("un periodo", periodId));
    }
}
