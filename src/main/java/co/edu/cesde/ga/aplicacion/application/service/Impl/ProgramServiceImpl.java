package co.edu.cesde.ga.aplicacion.application.service.Impl;

import co.edu.cesde.ga.aplicacion.application.service.ProgramService;
import co.edu.cesde.ga.aplicacion.domain.Programs;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceConflictException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Programs create(Programs program) {
        if (programRepository.existsByCode(program.getCode())) {
            throw new ResourceConflictException("Ya existe un programa con el código: " + program.getCode());
        }
        return programRepository.save(program);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Programs> findAll() {
        return programRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Programs> findById(Long id) {
        return programRepository.findById(id);
    }

    @Override
    public Optional<Programs> update(Programs program) {
        Optional<Programs> existing = programRepository.findById(program.getProgramId());
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        if (programRepository.existsByCodeAndProgramIdNot(program.getCode(), program.getProgramId())) {
            throw new ResourceConflictException("Ya existe un programa con el código: " + program.getCode());
        }

        Programs current = existing.get();
        current.setCode(program.getCode());
        current.setName(program.getName());

        return Optional.of(programRepository.save(current));
    }

    @Override
    public void deleteById(Long id) {
        if (!programRepository.existsById(id)) {
            throw new ResourceNotFoundException("un programa", id);
        }
        programRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return programRepository.existsById(id);
    }
}
