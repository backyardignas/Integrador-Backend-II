package co.edu.cesde.ga.aplicacion.application.service.Impl;

import co.edu.cesde.ga.aplicacion.application.service.TeacherService;
import co.edu.cesde.ga.aplicacion.domain.Teachers;
import co.edu.cesde.ga.aplicacion.domain.Users;
import co.edu.cesde.ga.aplicacion.domain.exceptions.DocumentAlreadyExistsException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceConflictException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.TeacherNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.repository.TeacherRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, UserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Teachers create(Teachers teacher, Long userId) {
        if (teacherRepository.existsByDocumentNumber(teacher.getDocumentNumber())) {
            throw new DocumentAlreadyExistsException(teacher.getDocumentNumber());
        }
        if (teacherRepository.existsByCode(teacher.getCode())) {
            throw new ResourceConflictException("Ya existe un profesor con el código: " + teacher.getCode());
        }
        teacher.setUser(findUser(userId));
        return teacherRepository.save(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teachers> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Teachers> findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Optional<Teachers> update(Teachers teacher, Long userId) {
        Optional<Teachers> existing = teacherRepository.findById(teacher.getTeacherId());
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        if (teacherRepository.existsByDocumentNumberAndTeacherIdNot(teacher.getDocumentNumber(), teacher.getTeacherId())) {
            throw new DocumentAlreadyExistsException(teacher.getDocumentNumber());
        }
        if (teacherRepository.existsByCodeAndTeacherIdNot(teacher.getCode(), teacher.getTeacherId())) {
            throw new ResourceConflictException("Ya existe un profesor con el código: " + teacher.getCode());
        }

        Teachers current = existing.get();
        current.setFirstName(teacher.getFirstName());
        current.setLastName(teacher.getLastName());
        current.setDocumentNumber(teacher.getDocumentNumber());
        current.setCode(teacher.getCode());
        current.setStatus(teacher.getStatus());
        current.setUser(findUser(userId));

        return Optional.of(teacherRepository.save(current));
    }

    @Override
    public void deleteById(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new TeacherNotFoundException(id);
        }
        teacherRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }

    private Users findUser(Long userId) {
        if (userId == null) {
            return null;
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("un usuario", userId));
    }
}
