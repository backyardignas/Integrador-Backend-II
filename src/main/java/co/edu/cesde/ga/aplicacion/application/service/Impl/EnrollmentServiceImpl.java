package co.edu.cesde.ga.aplicacion.application.service.Impl;

import co.edu.cesde.ga.aplicacion.application.service.EnrollmentService;
import co.edu.cesde.ga.aplicacion.domain.EnrollmentStatus;
import co.edu.cesde.ga.aplicacion.domain.Enrollments;
import co.edu.cesde.ga.aplicacion.domain.Periods;
import co.edu.cesde.ga.aplicacion.domain.Students;
import co.edu.cesde.ga.aplicacion.domain.Subjects;
import co.edu.cesde.ga.aplicacion.domain.exceptions.BusinessException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.EnrollmentNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceConflictException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.StudentNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.repository.EnrollmentsRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.PeriodsRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.StudentRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.SubjectsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentsRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final SubjectsRepository subjectsRepository;
    private final PeriodsRepository periodsRepository;

    public EnrollmentServiceImpl(EnrollmentsRepository enrollmentRepository,
                                 StudentRepository studentRepository,
                                 SubjectsRepository subjectsRepository,
                                 PeriodsRepository periodsRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.subjectsRepository = subjectsRepository;
        this.periodsRepository = periodsRepository;
    }

    @Override
    public Enrollments create(Long studentId, Long subjectId, Long periodId, LocalDate enrollmentDate) {
        Students student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        Subjects subject = subjectsRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("una materia", subjectId));
        Periods period = periodsRepository.findById(periodId)
                .orElseThrow(() -> new ResourceNotFoundException("un periodo", periodId));

        if (enrollmentRepository.existsByStudent_StudentIdAndSubject_SubjectIdAndPeriod_PeriodIdAndStatusNot(
                studentId, subjectId, periodId, EnrollmentStatus.CANCELLED)) {
            throw new ResourceConflictException("El estudiante " + studentId + " ya está matriculado en "
                    + subject.getName() + " para el periodo " + period.getName());
        }

        LocalDate date = enrollmentDate != null ? enrollmentDate : LocalDate.now();
        return enrollmentRepository.save(new Enrollments(null, student, subject, period, date, EnrollmentStatus.ACTIVE));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Enrollments> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Enrollments> findById(Long id) {
        return enrollmentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Enrollments> findByStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(studentId);
        }
        return enrollmentRepository.findByStudent_StudentId(studentId);
    }

    @Override
    public Enrollments updateStatus(Long id, EnrollmentStatus newStatus) {
        Enrollments enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
        EnrollmentStatus currentStatus = enrollment.getStatus();

        if (currentStatus == newStatus) {
            return enrollment;
        }
        if (currentStatus == EnrollmentStatus.FINISHED) {
            throw new BusinessException("Una matrícula finalizada no se puede modificar");
        }
        if (currentStatus == EnrollmentStatus.CANCELLED && newStatus == EnrollmentStatus.FINISHED) {
            throw new BusinessException("Una matrícula cancelada no se puede finalizar");
        }

        if (newStatus == EnrollmentStatus.ACTIVE) {
            Long studentId = enrollment.getStudent().getStudentId();
            Long subjectId = enrollment.getSubject().getSubjectId();
            Long periodId = enrollment.getPeriod().getPeriodId();
            if (enrollmentRepository.existsByStudent_StudentIdAndSubject_SubjectIdAndPeriod_PeriodIdAndStatusNotAndEnrollmentIdNot(
                    studentId, subjectId, periodId, EnrollmentStatus.CANCELLED, id)) {
                throw new ResourceConflictException("El estudiante " + studentId + " ya tiene otra matrícula vigente en esa materia y periodo");
            }
        }

        enrollment.setStatus(newStatus);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void deleteById(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new EnrollmentNotFoundException(id);
        }
        enrollmentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return enrollmentRepository.existsById(id);
    }
}
