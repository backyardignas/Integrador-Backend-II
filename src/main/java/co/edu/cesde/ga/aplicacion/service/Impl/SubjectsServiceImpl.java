package co.edu.cesde.ga.aplicacion.service.Impl;

import co.edu.cesde.ga.aplicacion.exceptions.ObjetoNoEncontradoException;
import co.edu.cesde.ga.aplicacion.exceptions.ValidacionDatosException;
import co.edu.cesde.ga.aplicacion.models.Subjects;
import co.edu.cesde.ga.aplicacion.repository.SubjectsRepository;
import co.edu.cesde.ga.aplicacion.service.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectsServiceImpl implements SubjectsService {

    private final SubjectsRepository subjectsRepository;

    @Autowired
    public SubjectsServiceImpl(SubjectsRepository subjectsRepository) {
        this.subjectsRepository = subjectsRepository;
    }

    @Override
    @Transactional
    public Subjects create(Subjects subject) {
        if (subject.getName() == null || subject.getName().trim().isEmpty()) {
            throw new ValidacionDatosException("El nombre de la materia es obligatorio.");
        }
        if (subject.getCredits() == null || subject.getCredits() <= 0) {
            throw new ValidacionDatosException("La materia debe tener créditos válidos.");
        }
        if (subject.getCode() == null || subject.getCode().trim().isEmpty()) {
            throw new ValidacionDatosException("El código de la materia es obligatorio.");
        }
        return subjectsRepository.save(subject);
    }

    @Override
    @Transactional
    public boolean delete(Long subjectId) {
        if (subjectsRepository.existsById(subjectId)) {
            subjectsRepository.deleteById(subjectId);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean update(Subjects subjectUpdate) {
        if (subjectUpdate.getSubjectId() != null && subjectsRepository.existsById(subjectUpdate.getSubjectId())) {
            subjectsRepository.save(subjectUpdate);
            return true;
        }
        return false;
    }

    @Override
    public Subjects findById(Long subjectId) {
        return subjectsRepository.findById(subjectId)
                .orElseThrow(() -> new ObjetoNoEncontradoException("No se encontró la materia"));
    }

    @Override
    public List<Subjects> findAll() {
        return subjectsRepository.findAll();
    }

    @Override
    public boolean existsById(Long subjectId) {
        return subjectsRepository.existsById(subjectId);
    }
}
