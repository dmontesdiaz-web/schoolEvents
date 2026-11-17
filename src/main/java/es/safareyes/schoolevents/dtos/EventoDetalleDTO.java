package es.safareyes.schoolevents.dtos;

import es.safareyes.schoolevents.entities.EstadoEvento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record EventoDetalleDTO(
        Integer id,
        String codigo,
        String titulo,
        String descripcion,
        String requisitos,
        LocalDate fechaInicio,
        LocalTime horaInicio,
        LocalDate fechaFin,
        LocalTime horaFin,
        Integer aforoMaximo,
        LocalDate fechaLimite,
        EstadoEvento estado,
        LugarDTO lugar,
        CategoriaDTO categoria,
        String organizador,
        List<CursoDTO> cursos,     // vacío = abierto a todo el centro
        Integer plazasLibres            // lo calcula el servicio
) {
}
