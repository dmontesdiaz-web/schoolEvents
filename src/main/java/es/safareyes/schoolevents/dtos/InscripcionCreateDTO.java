package es.safareyes.schoolevents.dtos;

import es.safareyes.schoolevents.entities.TipoInscripcion;
import jakarta.validation.constraints.NotNull;

public record InscripcionCreateDTO(
        @NotNull(message = "Hay que indicar el tipo de inscripción")
        TipoInscripcion tipo
) {
}
