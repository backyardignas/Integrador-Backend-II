package co.edu.cesde.ga.aplicacion.application.service.Impl;

import co.edu.cesde.ga.aplicacion.application.service.PeriodsService;
import co.edu.cesde.ga.aplicacion.domain.Periods;
import co.edu.cesde.ga.aplicacion.domain.exceptions.BusinessException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.repository.PeriodsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PeriodsServiceImpl implements PeriodsService {

    private final PeriodsRepository periodsRepository;

    public PeriodsServiceImpl(PeriodsRepository periodsRepository) {
        this.periodsRepository = periodsRepository;
    }

    @Override
    public Periods create(Periods period) {
        checkDates(period);
        return periodsRepository.save(period);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Periods> findAll() {
        return periodsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Periods> findById(Long id) {
        return periodsRepository.findById(id);
    }

    @Override
    public Optional<Periods> update(Periods period) {
        Optional<Periods> existing = periodsRepository.findById(period.getPeriodId());
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        checkDates(period);

        Periods current = existing.get();
        current.setName(period.getName());
        current.setStartDate(period.getStartDate());
        current.setEndDate(period.getEndDate());

        return Optional.of(periodsRepository.save(current));
    }

    @Override
    public void deleteById(Long id) {
        if (!periodsRepository.existsById(id)) {
            throw new ResourceNotFoundException("un periodo", id);
        }
        periodsRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return periodsRepository.existsById(id);
    }

    private void checkDates(Periods period) {
        if (!period.getEndDate().isAfter(period.getStartDate())) {
            throw new BusinessException("La fecha de fin debe ser posterior a la fecha de inicio");
        }
    }
}
