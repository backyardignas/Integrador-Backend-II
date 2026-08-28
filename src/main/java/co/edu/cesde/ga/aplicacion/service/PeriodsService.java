package co.edu.cesde.ga.aplicacion.service;

import co.edu.cesde.ga.aplicacion.models.Periods;
import java.util.List;


public interface PeriodsService {


    void createPeriod(Periods period);


    Periods getPeriodById(Long id);


    List<Periods> getAllPeriods();

    void updatePeriod(Periods period);



    void deletePeriod(Long id);
}
