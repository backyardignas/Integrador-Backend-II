package co.edu.cesde.ga.aplicacion.application.service.Impl;

import co.edu.cesde.ga.aplicacion.application.service.GradesService;
import co.edu.cesde.ga.aplicacion.domain.EnrollmentStatus;
import co.edu.cesde.ga.aplicacion.domain.Grades;
import co.edu.cesde.ga.aplicacion.domain.GroupsSubjects;
import co.edu.cesde.ga.aplicacion.domain.Students;
import co.edu.cesde.ga.aplicacion.domain.exceptions.BusinessException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceConflictException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.StudentNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.repository.EnrollmentsRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.GradesRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.GroupSubjectsRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GradesServiceImpl implements GradesService {

    private final GradesRepository gradesRepository;
    private final StudentRepository studentRepository;
    private final GroupSubjectsRepository groupSubjectsRepository;
    private final EnrollmentsRepository enrollmentsRepository;

    public GradesServiceImpl(GradesRepository gradesRepository,
                             StudentRepository studentRepository,
                             GroupSubjectsRepository groupSubjectsRepository,
                             EnrollmentsRepository enrollmentsRepository) {
        this.gradesRepository = gradesRepository;
        this.studentRepository = studentRepository;
        this.groupSubjectsRepository = groupSubjectsRepository;
        this.enrollmentsRepository = enrollmentsRepository;
    }

    @Override
    public Grades create(Long studentId, Long groupSubjectId, Double finalScore, String observation) {
        Students student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        GroupsSubjects groupSubject = groupSubjectsRepository.findById(groupSubjectId)
                .orElseThrow(() -> new ResourceNotFoundException("una materia de grupo", groupSubjectId));

        if (gradesRepository.existsByStudent_StudentIdAndGroupSubject_GroupSubjectId(studentId, groupSubjectId)) {
            throw new ResourceConflictException("El estudiante " + studentId + " ya tiene una nota registrada en esa materia del grupo");
        }

        Long subjectId = groupSubject.getSubject().getSubjectId();
        Long periodId = groupSubject.getGroup().getPeriod().getPeriodId();
        if (!enrollmentsRepository.existsByStudent_StudentIdAndSubject_SubjectIdAndPeriod_PeriodIdAndStatusNot(
                studentId, subjectId, periodId, EnrollmentStatus.CANCELLED)) {
            throw new BusinessException("El estudiante " + studentId + " no tiene una matrícula vigente en "
                    + groupSubject.getSubject().getName() + " para el periodo " + groupSubject.getGroup().getPeriod().getName());
        }

        return gradesRepository.save(new Grades(null, groupSubject, student, finalScore, observation));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Grades> findAll() {
        return gradesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Grades> findById(Long id) {
        return gradesRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Grades> findByStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(studentId);
        }
        return gradesRepository.findByStudent_StudentId(studentId);
    }

    @Override
    public Optional<Grades> update(Long id, Double finalScore, String observation) {
        Optional<Grades> existing = gradesRepository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        Grades current = existing.get();
        current.setFinalScore(finalScore);
        current.setObservation(observation);

        return Optional.of(gradesRepository.save(current));
    }

    @Override
    public void deleteById(Long id) {
        if (!gradesRepository.existsById(id)) {
            throw new ResourceNotFoundException("una nota", id);
        }
        gradesRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return gradesRepository.existsById(id);
    }
}
