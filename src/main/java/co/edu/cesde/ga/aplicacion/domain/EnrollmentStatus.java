package co.edu.cesde.ga.aplicacion.domain;

import co.edu.cesde.ga.aplicacion.domain.exceptions.BusinessException;

public enum EnrollmentStatus {
    ACTIVE,
    CANCELLED,
    FINISHED;

    public static EnrollmentStatus from(String value) {
        if (value == null || value.isBlank()) {
            throw new BusinessException("El estado de la matrícula es obligatorio");
        }
        try {
            return EnrollmentStatus.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Estado de matrícula inválido: " + value + ". Valores permitidos: ACTIVE, CANCELLED, FINISHED");
        }
    }
}
