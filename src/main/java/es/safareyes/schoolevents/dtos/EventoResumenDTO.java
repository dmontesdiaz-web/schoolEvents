package es.safareyes.schoolevents.dtos;

import es.safareyes.schoolevents.entities.EstadoEvento;

import java.time.LocalDate;
import java.time.LocalTime;

// Tarjeta de evento para los listados
public record EventoResumenDTO(
        Integer id,
        String codigo,
        String titulo,
        LocalDate fechaInicio,
        LocalTime horaInicio,
        LocalDate fechaFin,
        LocalTime horaFin,
        EstadoEvento estado,
        String lugar,
        String categoria,
        String icono,
        String color
) {
}
