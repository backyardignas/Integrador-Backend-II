package co.edu.cesde.ga.aplicacion.controller.dto;

import co.edu.cesde.ga.aplicacion.domain.Students;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateStudentDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Size(max = 50) String documentNumber,
        @NotBlank @Size(max = 50) String code,
        @NotNull @Past
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate,
        @NotBlank String status,
        Long userId
) {
    public Students toStudent() {
        return new Students(null, null, firstName.trim(), lastName.trim(), documentNumber.trim(),
                code.trim().toUpperCase(), birthDate, status.trim().toUpperCase());
    }
}
