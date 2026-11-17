package es.safareyes.schoolevents.dtos;

import es.safareyes.schoolevents.entities.EstadoInscripcion;
import es.safareyes.schoolevents.entities.TipoInscripcion;

public record AgendaEventoDTO(
        Integer idInscripcion,
        TipoInscripcion tipo,
        EstadoInscripcion estado,
        Integer posicionEspera,
        EventoResumenDTO evento
) {
}
