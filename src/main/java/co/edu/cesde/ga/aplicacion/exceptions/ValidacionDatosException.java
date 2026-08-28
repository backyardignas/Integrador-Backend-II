package co.edu.cesde.ga.aplicacion.exceptions;

public class ValidacionDatosException extends RuntimeException {
    public ValidacionDatosException(String mensaje) {
        super(mensaje);
    }
}