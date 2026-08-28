package co.edu.cesde.ga.aplicacion.service.Impl;

import co.edu.cesde.ga.aplicacion.models.Enrollments;
import co.edu.cesde.ga.aplicacion.repository.EnrollmentsRepository;
import co.edu.cesde.ga.aplicacion.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentsRepository enrollmentRepository;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentsRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    @Transactional
    public void enrollStudent(Enrollments enrollment) {
        enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollments getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Enrollments> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudent_StudentId(studentId);
    }

    @Override
    public List<Enrollments> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    @Transactional
    public void cancelEnrollment(Long id) {

        enrollmentRepository.deleteById(id);
    }
}
