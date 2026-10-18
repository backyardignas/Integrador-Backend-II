package co.edu.cesde.ga.aplicacion.domain.exceptions;

public class TeacherNotFoundException extends ResourceNotFoundException {

    public TeacherNotFoundException(Long id) {
        super("un profesor", id);
    }
}
