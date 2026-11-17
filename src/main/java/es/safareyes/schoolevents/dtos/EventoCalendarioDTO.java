package es.safareyes.schoolevents.dtos;

import java.time.LocalTime;

public record EventoCalendarioDTO(
        Integer id,
        String titulo,
        LocalTime horaInicio,
        LocalTime horaFin,
        String lugar,
        String categoria,
        String icono,
        String color
) {
}
