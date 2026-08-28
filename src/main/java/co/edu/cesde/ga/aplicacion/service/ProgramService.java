package co.edu.cesde.ga.aplicacion.service;

import co.edu.cesde.ga.aplicacion.models.Programs;
import java.util.List;

public interface ProgramService {
    Programs createProgram(Programs program);
    List<Programs> getAllPrograms();
}