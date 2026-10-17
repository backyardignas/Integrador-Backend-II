package co.edu.cesde.ga.aplicacion.controller.dto.response;

import co.edu.cesde.ga.aplicacion.domain.Programs;

public record ProgramResponseDto(Long id, String code, String name) {

    public static ProgramResponseDto from(Programs program) {
        return new ProgramResponseDto(program.getProgramId(), program.getCode(), program.getName());
    }
}
