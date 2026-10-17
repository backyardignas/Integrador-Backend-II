package co.edu.cesde.ga.aplicacion.domain.exceptions;

public class DocumentAlreadyExistsException extends ResourceConflictException {

    public DocumentAlreadyExistsException(String documentNumber) {
        super("Ya existe una persona registrada con el documento: " + documentNumber);
    }
}
