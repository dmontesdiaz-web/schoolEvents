package es.safareyes.schoolevents.dtos;

import jakarta.validation.constraints.NotNull;

public record AsistenciaDTO(
        @NotNull(message = "Hay que indicar si ha asistido")
        Boolean asistencia
) {
}
