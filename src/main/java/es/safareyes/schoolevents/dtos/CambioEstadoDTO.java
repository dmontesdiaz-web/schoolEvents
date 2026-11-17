package es.safareyes.schoolevents.dtos;

import es.safareyes.schoolevents.entities.EstadoEvento;
import jakarta.validation.constraints.NotNull;

public record CambioEstadoDTO(
        @NotNull(message = "Hay que indicar el nuevo estado")
        EstadoEvento estado
) {
}
