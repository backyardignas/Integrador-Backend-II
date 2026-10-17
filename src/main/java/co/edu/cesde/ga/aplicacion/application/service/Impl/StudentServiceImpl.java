package co.edu.cesde.ga.aplicacion.application.service.Impl;

import co.edu.cesde.ga.aplicacion.application.service.StudentService;
import co.edu.cesde.ga.aplicacion.domain.Students;
import co.edu.cesde.ga.aplicacion.domain.Users;
import co.edu.cesde.ga.aplicacion.domain.exceptions.DocumentAlreadyExistsException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceConflictException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.StudentNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.repository.StudentRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Students create(Students student, Long userId) {
        if (studentRepository.existsByDocumentNumber(student.getDocumentNumber())) {
            throw new DocumentAlreadyExistsException(student.getDocumentNumber());
        }
        if (studentRepository.existsByCode(student.getCode())) {
            throw new ResourceConflictException("Ya existe un estudiante con el código: " + student.getCode());
        }
        student.setUser(findUser(userId));
        return studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Students> findAll() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Students> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Students> update(Students student, Long userId) {
        Optional<Students> existing = studentRepository.findById(student.getStudentId());
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        if (studentRepository.existsByDocumentNumberAndStudentIdNot(student.getDocumentNumber(), student.getStudentId())) {
            throw new DocumentAlreadyExistsException(student.getDocumentNumber());
        }
        if (studentRepository.existsByCodeAndStudentIdNot(student.getCode(), student.getStudentId())) {
            throw new ResourceConflictException("Ya existe un estudiante con el código: " + student.getCode());
        }

        Students current = existing.get();
        current.setFirstName(student.getFirstName());
        current.setLastName(student.getLastName());
        current.setDocumentNumber(student.getDocumentNumber());
        current.setCode(student.getCode());
        current.setBirthDate(student.getBirthDate());
        current.setStatus(student.getStatus());
        current.setUser(findUser(userId));

        return Optional.of(studentRepository.save(current));
    }

    @Override
    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }

    private Users findUser(Long userId) {
        if (userId == null) {
            return null;
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("un usuario", userId));
    }
}
