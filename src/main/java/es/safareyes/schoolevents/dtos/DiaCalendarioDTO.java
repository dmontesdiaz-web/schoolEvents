package es.safareyes.schoolevents.dtos;

import java.time.LocalDate;
import java.util.List;

public record DiaCalendarioDTO(
        LocalDate dia,
        List<EventoCalendarioDTO> eventos
) {
}
