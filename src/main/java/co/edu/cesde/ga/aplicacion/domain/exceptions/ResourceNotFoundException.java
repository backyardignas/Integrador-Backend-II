package co.edu.cesde.ga.aplicacion.domain.exceptions;

public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resource, Long id) {
        super("No se encontró " + resource + " con el ID: " + id);
    }
}
