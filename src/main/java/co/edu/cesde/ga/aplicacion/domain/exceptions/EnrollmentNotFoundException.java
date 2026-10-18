package co.edu.cesde.ga.aplicacion.domain.exceptions;

public class EnrollmentNotFoundException extends ResourceNotFoundException {

    public EnrollmentNotFoundException(Long id) {
        super("una matrícula", id);
    }
}
