package co.edu.cesde.ga.aplicacion.domain.exceptions;

public class StudentNotFoundException extends ResourceNotFoundException {

    public StudentNotFoundException(Long id) {
        super("un estudiante", id);
    }
}
