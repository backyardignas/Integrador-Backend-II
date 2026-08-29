package co.edu.cesde.ga.aplicacion.service.Impl;

import co.edu.cesde.ga.aplicacion.exceptions.ObjetoNoEncontradoException;
import co.edu.cesde.ga.aplicacion.exceptions.ValidacionDatosException;
import co.edu.cesde.ga.aplicacion.models.Students;
import co.edu.cesde.ga.aplicacion.repository.StudentRepository;
import co.edu.cesde.ga.aplicacion.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public Students create(Students student) {
        if (student == null) {
            throw new ValidacionDatosException("El estudiante no puede ser nulo.");
        }
        if (student.getCode() == null || student.getCode().trim().isEmpty()) {
            throw new ValidacionDatosException("El código del estudiante es obligatorio.");
        }
        if (student.getDocumentNumber() == null || student.getDocumentNumber().trim().isEmpty()) {
            throw new ValidacionDatosException("El número de documento es obligatorio.");
        }
        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) {
            throw new ValidacionDatosException("El nombre del estudiante es obligatorio.");
        }
        if (student.getLastName() == null || student.getLastName().trim().isEmpty()) {
            throw new ValidacionDatosException("El apellido del estudiante es obligatorio.");
        }
        if (student.getBirthDate() == null) {
            throw new ValidacionDatosException("La fecha de nacimiento es obligatoria.");
        }
        if (student.getStatus() == null || student.getStatus().trim().isEmpty()) {
            throw new ValidacionDatosException("El estado del estudiante es obligatorio.");
        }


        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public boolean delete(Long studentId) {
        if (studentId == null) {
            throw new ValidacionDatosException("El ID del estudiante es obligatorio.");
        }

        findById(studentId);


        studentRepository.deleteById(studentId);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Students studentUpdate) {
        if (studentUpdate == null) {
            throw new ValidacionDatosException("El estudiante no puede ser nulo.");
        }
        if (studentUpdate.getStudentId() == null) {
            throw new ValidacionDatosException("El ID del estudiante es obligatorio para actualizar.");
        }
        if (studentUpdate.getFirstName() == null || studentUpdate.getFirstName().trim().isEmpty()) {
            throw new ValidacionDatosException("El nombre del estudiante es obligatorio.");
        }
        if (studentUpdate.getLastName() == null || studentUpdate.getLastName().trim().isEmpty()) {
            throw new ValidacionDatosException("El apellido del estudiante es obligatorio.");
        }


        findById(studentUpdate.getStudentId());


        studentRepository.save(studentUpdate);
        return true;
    }

    @Override
    public Students findById(Long studentId) {
        if (studentId == null) {
            throw new ValidacionDatosException("El ID del estudiante es obligatorio.");
        }


        Students student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            throw new ObjetoNoEncontradoException("No se encontró un estudiante con el ID: " + studentId);
        }

        return student;
    }

    @Override
    public Students findByDocumentNumber(String documentNumber) {
        if (documentNumber == null || documentNumber.trim().isEmpty()) {
            throw new ValidacionDatosException("El número de documento es obligatorio.");
        }

        return studentRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new ObjetoNoEncontradoException(
                        "No se encontró un estudiante con el número de documento: " + documentNumber));
    }

    @Override
    public List<Students> findAll() {
        return studentRepository.findAll();
    }
}
