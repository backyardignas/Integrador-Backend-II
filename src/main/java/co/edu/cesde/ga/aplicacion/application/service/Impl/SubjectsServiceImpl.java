package co.edu.cesde.ga.aplicacion.application.service.Impl;

import co.edu.cesde.ga.aplicacion.application.service.SubjectsService;
import co.edu.cesde.ga.aplicacion.domain.Programs;
import co.edu.cesde.ga.aplicacion.domain.Subjects;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceConflictException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.repository.ProgramRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.SubjectsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubjectsServiceImpl implements SubjectsService {

    private final SubjectsRepository subjectsRepository;
    private final ProgramRepository programRepository;

    public SubjectsServiceImpl(SubjectsRepository subjectsRepository, ProgramRepository programRepository) {
        this.subjectsRepository = subjectsRepository;
        this.programRepository = programRepository;
    }

    @Override
    public Subjects create(Subjects subject, Long programId) {
        if (subjectsRepository.existsByCode(subject.getCode())) {
            throw new ResourceConflictException("Ya existe una materia con el código: " + subject.getCode());
        }
        subject.setProgram(findProgram(programId));
        return subjectsRepository.save(subject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subjects> findAll() {
        return subjectsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Subjects> findById(Long id) {
        return subjectsRepository.findById(id);
    }

    @Override
    public Optional<Subjects> update(Subjects subject, Long programId) {
        Optional<Subjects> existing = subjectsRepository.findById(subject.getSubjectId());
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        if (subjectsRepository.existsByCodeAndSubjectIdNot(subject.getCode(), subject.getSubjectId())) {
            throw new ResourceConflictException("Ya existe una materia con el código: " + subject.getCode());
        }

        Subjects current = existing.get();
        current.setCode(subject.getCode());
        current.setName(subject.getName());
        current.setCredits(subject.getCredits());
        current.setProgram(findProgram(programId));

        return Optional.of(subjectsRepository.save(current));
    }

    @Override
    public void deleteById(Long id) {
        if (!subjectsRepository.existsById(id)) {
            throw new ResourceNotFoundException("una materia", id);
        }
        subjectsRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return subjectsRepository.existsById(id);
    }

    private Programs findProgram(Long programId) {
        return programRepository.findById(programId)
                .orElseThrow(() -> new ResourceNotFoundException("un programa", programId));
    }
}
